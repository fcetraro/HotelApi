package com.ml.HotelApi.service.implementation;

import com.ml.HotelApi.filter.HotelPredicate;
import com.ml.HotelApi.model.HotelDTO;
import com.ml.HotelApi.repository.IHotelRepository;
import com.ml.HotelApi.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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
}

