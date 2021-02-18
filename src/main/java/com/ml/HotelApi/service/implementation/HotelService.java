package com.ml.HotelApi.service.implementation;

import com.ml.HotelApi.exception.implementation.DatesNotValidException;
import com.ml.HotelApi.exception.implementation.ProvinceNotFoundException;
import com.ml.HotelApi.filter.HotelPredicate;
import com.ml.HotelApi.model.HotelDTO;
import com.ml.HotelApi.repository.IHotelRepository;
import com.ml.HotelApi.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private IHotelRepository hotels;
    @Autowired
    public HotelService(IHotelRepository hotelRepository) {
        this.hotels = hotelRepository;
    }

    private List<HotelDTO> applyFilters(List<HotelDTO> hotels, Map<String, String> filter){
        HotelPredicate filters = new HotelPredicate();
        Predicate<HotelDTO> predicate = filters.getCombinedPredicateFromDTO(filter);
        return hotels.stream().filter(predicate).collect(toList());
    }
    private boolean isDestinationValid(String destination){
        for (HotelDTO hotel:hotels.getAll()) {
            if(hotel.getCity().equals(destination)) return true;
        }
        return false;
    }
    private void validateFilters(Map<String, String> filters){
        if(filters!=null) {
            if (filters.containsKey("destination")) {
                if(!isDestinationValid(filters.get("destination"))){
                    throw new ProvinceNotFoundException(filters.get("destination"),new Exception("Province not found"));
                }
            }
            if (filters.containsKey("dateTo")&&filters.containsKey("dateFrom")) {
                try{
                    Date dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(filters.get("dateFrom"));
                    Date dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(filters.get("dateTo"));
                    if(dateFrom.after(dateTo)){
                        throw new DatesNotValidException(new Exception());
                    }
                } catch (ParseException e) {
                    throw new DatesNotValidException(e);
                }

            }
        }
    }
    @Override
    public List<HotelDTO> get(Map<String, String> filters) {
        validateFilters(filters);
        return applyFilters(hotels.getAll(), filters);
    }
}

