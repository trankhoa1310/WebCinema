package com.vn.cines_start.Service.Iplm;

import com.vn.cines_start.Model.Room;
import com.vn.cines_start.Repository.RoomRepository;
import com.vn.cines_start.Service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomImplement implements IRoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findRoomById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room edit(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
