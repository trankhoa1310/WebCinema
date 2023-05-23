package com.vn.cines_start.Service;


import com.vn.cines_start.Model.Room;

import java.util.List;
import java.util.Optional;

public interface IRoomService {
    List<Room> findAll();

    Optional<Room> findRoomById(Long id);
    Room create(Room room);

    Room edit(Room room);

    void delete(Long id);
}
