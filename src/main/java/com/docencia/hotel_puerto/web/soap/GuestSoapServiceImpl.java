package com.docencia.hotel_puerto.web.soap;

import com.docencia.hotel_puerto.domain.model.Guest;
import com.docencia.hotel_puerto.domain.model.GuestPreferences;
import com.docencia.hotel_puerto.service.api.GuestService;
import jakarta.jws.WebService;
import org.springframework.stereotype.Service;

import java.util.List;

@WebService(serviceName = "GuestService", portName = "GuestPort", targetNamespace = "http://ies.puerto.es/ws/guest", endpointInterface = "com.docencia.hotel_puerto.web.soap.GuestSoapService")
@Service
public class GuestSoapServiceImpl implements GuestSoapService {
    private final GuestService guestService;

    public GuestSoapServiceImpl(GuestService guestService) {
        this.guestService = guestService;
    }

    @Override
    public List<Guest> findAll() {
        return guestService.findAll();
    }

    @Override
    public Guest findById(Long id) {
        return guestService.findById(id);
    }

    @Override
    public Guest create(String fullName, String email, String phone, String roomType, String boardType) {
        Guest newGuest = new Guest(null, fullName, email, phone, null);
        GuestPreferences guestPreferences = new GuestPreferences(null, newGuest.getId(), roomType, boardType);
        newGuest.setGuestPreferences(guestPreferences);
        return guestService.save(newGuest);
    }

    @Override
    public Guest update(Long id, String fullName, String email, String phone, String roomType, String boardType) {
        Guest guestToUpdate = guestService.findById(id);
        guestToUpdate.setFullName(fullName);
        guestToUpdate.setEmail(email);
        guestToUpdate.setPhone(phone);
        GuestPreferences guestPreferences = new GuestPreferences(null, id, roomType, boardType);
        if (guestToUpdate.getGuestPreferences() != null) {
            guestPreferences = guestToUpdate.getGuestPreferences();
            guestPreferences.setBoardType(boardType);
            guestPreferences.setRoomType(roomType);
        }
        guestToUpdate.setGuestPreferences(guestPreferences);
        return guestService.save(guestToUpdate);
    }

    @Override
    public boolean delete(Long id) {
        return guestService.delete(id);
    }
}
