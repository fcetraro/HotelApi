package com.ml.HotelApi.service.implementation;

import com.ml.HotelApi.exception.implementation.NotValidDateException;
import com.ml.HotelApi.filter.HotelFilter;
import com.ml.HotelApi.filter.HotelPredicate;
import com.ml.HotelApi.filter.concret.DateFrom;
import com.ml.HotelApi.filter.concret.DateTo;
import com.ml.HotelApi.model.HotelDTO;
import com.ml.HotelApi.repository.IHotelRepository;
import com.ml.HotelApi.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.ml.HotelApi.util.DateFormat.DATE_FORMAT;
import static com.ml.HotelApi.util.Validator.validateDestination;
import static com.ml.HotelApi.util.Validator.validateDatesString;
import static java.util.stream.Collectors.toList;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private IHotelRepository hotels;
    @Autowired
    public HotelService(IHotelRepository hotelRepository) {
        this.hotels = hotelRepository;
    }

    private static String provinceKey = "destination";

    private List<HotelDTO> applyFilters(List<HotelDTO> hotels, Map<String, String> filter){
        HotelPredicate filters = new HotelPredicate();
        Predicate<HotelDTO> predicate = filters.getCombinedPredicateFromDTO(filter);
        return hotels.stream().filter(predicate).collect(toList());
    }

    private void validateFilters(Map<String, String> filters){
        if(filters!=null) {
            if (filters.containsKey(provinceKey)) {
                validateDestination(filters.get(provinceKey),hotels.getAll());
            }
            if (filters.containsKey("dateTo") && filters.containsKey("dateFrom")) {
                validateDates(filters);
            }
        }
    }

    private void validateDates(Map<String, String> filters){
        validateDatesString(filters.get("dateFrom"),filters.get("dateTo"));
    }

    @Override
    public List<HotelDTO> get(Map<String, String> filters) {
        validateFilters(filters);
        return applyFilters(hotels.getAll(), filters);
    }

    @Override
    public void modifyAvailability(Map<String, String> filters, Map<String, String> map) {
        List<HotelDTO> allMatches = get(filters);
        HotelDTO booked = allMatches.get(0);
        HotelFilter dateTo = new DateTo();
        HotelFilter dateFrom = new DateFrom();
        try{
            Date dFrom = new SimpleDateFormat(DATE_FORMAT).parse(map.get(dateFrom.getFilterName()));
            Date dTo = new SimpleDateFormat(DATE_FORMAT).parse(map.get(dateTo.getFilterName()));
            HotelDTO newBooked = new HotelDTO(booked.getCode(), booked.getName(), booked.getCity(),
                    booked.getRoomType(), true,dFrom,dTo, booked.getPrice());
            hotels.add(newBooked);
        } catch (ParseException e) {
            throw new NotValidDateException(e);
        }
    }
}

