package com.ml.HotelApi.repository.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.HotelApi.exception.implementation.NotValidDateException;
import com.ml.HotelApi.model.Availability;
import com.ml.HotelApi.model.Hotel;
import com.ml.HotelApi.model.HotelDTO;
import com.ml.HotelApi.model.HotelJSONDTO;
import com.ml.HotelApi.repository.IHotelRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.ml.HotelApi.util.DateFormat.DATE_FORMAT;

@Repository
public class JsonHotelRepository implements IHotelRepository {
    private final static String db = "hotels.json";
    private static List<Hotel> hotels;
    private static List<Availability> availabilities;

    private List<HotelJSONDTO> loadFromFile() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:"+db);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<HotelJSONDTO>> typeRef = new TypeReference<>(){};
        List<HotelJSONDTO> hotelsFromJson = null;
        try{
            hotelsFromJson = objectMapper.readValue(file, typeRef);
        } catch (IOException e){
            e.printStackTrace();
        }
        return hotelsFromJson;
    }

    private void loadInitialHotels(){
        if (hotels==null){
            List<HotelDTO> hotelDTOS = parseFromJson(loadFromFile());
            hotels=parseHotelFromDTO(hotelDTOS);
            availabilities=parseAvailabilityFromDTO(hotelDTOS);
        }
    }

    private List<Hotel> parseHotelFromDTO(List<HotelDTO> list) {
        List<Hotel> hotelList = new ArrayList<>();
        for (HotelDTO hotelDTO:list) {
            Hotel hotel = new Hotel(hotelDTO.getCode(), hotelDTO.getName(), hotelDTO.getCity(),
                    hotelDTO.getRoomType().toUpperCase(Locale.ROOT), hotelDTO.getPrice());
            hotelList.add(hotel);
        }
        return hotelList;
    }

    private List<Availability> parseAvailabilityFromDTO(List<HotelDTO> list) {
        List<Availability> availabilities = new ArrayList<>();
        for (HotelDTO hotelDTO:list) {
            Availability availability = new Availability();
            availability.setAvailable(!hotelDTO.isBooked());
            availability.setAvailableSince(hotelDTO.getAvailableSince());
            availability.setAvailableUntil(hotelDTO.getAvailableUntil());
            availability.setCode(hotelDTO.getCode());
            availabilities.add(availability);
        }
        return availabilities;
    }

    private List<HotelDTO> parseFromEntities() {
        List<HotelDTO> hotelList = new ArrayList<>();
        for (Hotel hotel:hotels) {
            List<Availability> availabilitiesOfHotel = getAvailabilitiesByCode(hotel.getCode());
            if(availabilitiesOfHotel.size()==0){
                List<Availability> availabilities = this.availabilities;
                HotelDTO newHotelDto = new HotelDTO(hotel.getCode(), hotel.getName(), hotel.getCity(),
                        hotel.getRoomType().toUpperCase(Locale.ROOT), false, new Date(0), new Date(0),
                        hotel.getPrice());
                hotelList.add(newHotelDto);
            }
            for (int i = 0; i < availabilitiesOfHotel.size(); i++) {
                HotelDTO newHotelDto = new HotelDTO(hotel.getCode(), hotel.getName(), hotel.getCity(),
                        hotel.getRoomType().toUpperCase(Locale.ROOT), !availabilitiesOfHotel.get(i).getAvailable(),
                        availabilitiesOfHotel.get(i).getAvailableSince(),
                        availabilitiesOfHotel.get(i).getAvailableUntil(), hotel.getPrice());
                hotelList.add(newHotelDto);
            }
        }
        return hotelList;
    }

    private List<Availability> getAvailabilitiesByCode(String code){
        List<Availability> list = new ArrayList<>();
        for (Availability availability:availabilities) {
            if(availability.getCode().equals(code)){
                list.add(availability);
            }
        }
        return list;
    }

    private List<HotelDTO> parseFromJson(List<HotelJSONDTO> list) {
        List<HotelDTO> hotelList = new ArrayList<>();
        for (HotelJSONDTO jsonDto:list) {
            try {
                Date dateFrom = new SimpleDateFormat(DATE_FORMAT).parse(jsonDto.getAvailableSince());
                Date dateTo = new SimpleDateFormat(DATE_FORMAT).parse(jsonDto.getAvailableUntil());
                HotelDTO hotel = new HotelDTO(jsonDto.getCode(), jsonDto.getName(), jsonDto.getCity(),
                        jsonDto.getRoomType().toUpperCase(Locale.ROOT), jsonDto.isBooked(),dateFrom,dateTo,
                        jsonDto.getPrice());
                hotelList.add(hotel);
            } catch (ParseException e){
                throw new NotValidDateException(e);
            }
        }
        return hotelList;
    }

    @Override
    public List<HotelDTO> getAllDTO() {
        loadInitialHotels();
        return parseFromEntities();
    }

    @Override
    public void add(Availability newAvailability) {
        loadInitialHotels();
        availabilities.add(newAvailability);
    }
}

