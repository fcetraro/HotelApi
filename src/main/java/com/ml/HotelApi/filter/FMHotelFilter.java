package com.ml.HotelApi.filter;

import com.ml.HotelApi.exception.implementation.FilterNotFoundException;
import com.ml.HotelApi.filter.concret.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FMHotelFilter {
    public static List<HotelFilter> getFilters(Map<String, String> mapFilters){
        List<HotelFilter> filters = new ArrayList<>();
        Iterator it = mapFilters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            HotelFilter concretFilter = getConcretFilter((String)pair.getKey(), (String)pair.getValue());
            if(concretFilter!=null){
                filters.add(concretFilter);
            }
            it.remove();
        }
        return filters;
    }

    private static HotelFilter getConcretFilter(String filter, String value) {
        for (HotelFilter concretFilter:getAllFilters()) {
            if(concretFilter.matchFilterName(filter)) {
                concretFilter.setValue(value);
                return concretFilter;
            }
        }
        throw new FilterNotFoundException("Filtro [" + filter + "] no reconocido.", new Exception());
    }

    private static List<HotelFilter> getAllFilters(){
        List<HotelFilter> allFilters = new ArrayList<>();
        allFilters.add(new DateFrom());
        allFilters.add(new DateTo());
        allFilters.add(new Destination());
        allFilters.add(new RoomType());
        allFilters.add(new Code());
        allFilters.add(new Booked());
        return allFilters;
    }
}
