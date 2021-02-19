package com.ml.HotelApi.repository.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.HotelApi.exception.implementation.NotValidDateException;
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
    private static List<HotelDTO> hotels;

    private List<HotelJSONDTO> loadArticlesFromFile() {
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

    private void loadInitialArticles(){
        if (hotels==null){
            hotels = parseFromJson(loadArticlesFromFile());
        }
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
    public List<HotelDTO> getAll() {
        loadInitialArticles();
        return hotels;
    }

    @Override
    public void add(HotelDTO hotel) {
        loadInitialArticles();
        hotels.add(hotel);
    }
}

