package com.rc.simpleserver.engine.endpoint.factory;

import com.rc.simpleserver.engine.endpoint.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispatcherFactory {
    @Autowired
    HelloDispatcher helloDispatcher;
    
    @Autowired
    ShortTermDispatcher shortTermDispatcher; 

    @Autowired
    ReservationsByArrivalDispatcher reservationsByArrivalDispatcher;

    @Autowired
    GetGuestMarketingInformationDispatcher getGuestMarketingInformationDispatcher;

    @Autowired
    AccessPersonsDispatcher accessPersonsDispatcher;

    @Autowired
    PostToken postToken;

    @Autowired
    DevicesDispatcher devicesDispatcher;
    
    @Autowired
    DefaultDispatcher defaultDispatcher;

    @Autowired
    JsonListDispatcher jsonListDispatcher;
    
    public IEndPointDispatcher getEndPointDispatcher(String endpointName) {
        IEndPointDispatcher dispatcher;
        switch (endpointName) {
            case "hello":
                dispatcher = helloDispatcher;
                break;
            case "GetShortTermProperty":
            case "GetShortTermProperties":
                dispatcher = shortTermDispatcher;
                break;
            case "GetReservationsByArrivalDate":
                dispatcher = reservationsByArrivalDispatcher;
                break;
            case "GetGuestMarketingInformation":
                dispatcher = getGuestMarketingInformationDispatcher;
                break;
            case "token":
                dispatcher = postToken;
                break;
            case "devices":
                dispatcher = devicesDispatcher;
                break;
            case "access_persons":
                dispatcher = accessPersonsDispatcher;
                break;
            case "jsonlist":
                dispatcher = jsonListDispatcher;
                break;
            default:
                dispatcher = defaultDispatcher;
                break;
        }
        
        return dispatcher;
    }
}
