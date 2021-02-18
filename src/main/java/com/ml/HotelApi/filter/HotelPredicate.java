package com.ml.HotelApi.filter;

import com.ml.HotelApi.model.HotelDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.ml.HotelApi.filter.FMHotelFilter.getFilters;

public class HotelPredicate {
    public Predicate<HotelDTO> getCombinedPredicateFromDTO(Map<String, String> filters){
        return getCombinedPredicate(filters);
    }
    private Predicate<HotelDTO> getCombinedPredicate(Map<String, String> mapFilters){
        List<HotelFilter> allFilters = getFilters(mapFilters);
        List<Predicate<HotelDTO>> allPredicates = new ArrayList<>();
        for (HotelFilter filter:allFilters) {
            allPredicates.add(filter.getPredicate());
        }
        return allPredicates.stream().reduce(w -> true, Predicate::and);
    }
}
