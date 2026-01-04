package com.docencia.hotel_puerto.web.soap;

import com.docencia.hotel_puerto.domain.model.Guest;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "http://ies.puerto.es/ws/guest", name = "GuestPortType")
public interface GuestSoapService {
    @WebMethod(operationName = "findAll")
    List<Guest> findAll();

    @WebMethod(operationName = "findById")
    Guest findById(@WebParam(name = "id") Long id);

    @WebMethod(operationName = "create")
    Guest create(@WebParam(name = "fullName") String fullName, @WebParam(name = "email") String email, @WebParam(name = "phone") String phone, @WebParam(name = "roomType") String roomType, @WebParam(name = "boardType") String boardType);

    @WebMethod(operationName = "update")
    Guest update(@WebParam(name = "id") Long id, @WebParam(name = "fullName") String fullName, @WebParam(name = "email") String email, @WebParam(name = "phone") String phone, @WebParam(name = "roomType") String roomType, @WebParam(name = "boardType") String boardType);

    @WebMethod(operationName = "delete")
    boolean delete(@WebParam(name = "id") Long id);
}
