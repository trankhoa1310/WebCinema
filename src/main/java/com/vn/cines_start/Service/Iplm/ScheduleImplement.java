package com.vn.cines_start.Service.Iplm;

import com.vn.cines_start.Model.Movie;
import com.vn.cines_start.Model.Schedule;
import com.vn.cines_start.Repository.ScheduleRepository;
import com.vn.cines_start.Service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class ScheduleImplement implements IScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    
    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }
    
    
    
    @Override
    public Schedule create(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule edit(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Long id) {
    scheduleRepository.deleteById(id);
    }

	@Override
	public List<Schedule> findScheduleByShowTime(LocalDate showTime) {
		return scheduleRepository.findAllByShowTime(showTime);
	}
	
	@Override
	public List<Date> findScheduleByMovieId(Long movieId) {
		return scheduleRepository.findAllByMovieId(movieId);
	}

	@Override
	public List<Time> findAllByShowTime(Long movieId, Date showDate) {
		return scheduleRepository.findAllByShowTime(movieId,showDate);
	}
	@Override
	public List<Long> findAllByShowDate(Date showDate){
		return scheduleRepository.findAllByShowDate(showDate);
	}
	@Override
	public List<Schedule> findAllByShowDate1(Date showDate){
		return scheduleRepository.findAllByShowDate1(showDate);
	}
	
	@Override
	public List<Schedule> findAllScheduleByMovieId1(Long movieId){
		return scheduleRepository.findAllScheduleByMovieId1(movieId);
	}


	@Override
	public Long findRoomByMovieAndDateTime(Long movieId, Date showDate, Time showTime) {
		return scheduleRepository.findRoomByMovieAndDateTime(movieId, showDate, showTime);
	}


	@Override
	public List<Time> showTimeByMovieAndRoomDate(Long movieId, Long roomId, Date showDate) {
		return scheduleRepository.showTimeByMovieAndRoomDate(movieId, roomId, showDate);
	}


	@Override
	public Schedule showScheduleID(Long movieId, Long roomId, Date showDate, Time showTime) {
		return scheduleRepository.showScheduleID(movieId, roomId, showDate, showTime);
	}


	@Override
	public List<Schedule> findEndTime( Long roomId, Date showDate) {
		return scheduleRepository.findEndTime( roomId , showDate);
	}
	
	@Override
	public List<Time> findEndTime2(Time showTime, int duration ) {
		return scheduleRepository.findEndTime2(showTime,duration );
	}


	@Override
	public Long findMovieBySchedule(Long scheduleID) {
		return  scheduleRepository.findMovieBySchedule(scheduleID);
	}


	@Override
	public List<Long> checkHasSchedule(Long movieID) {
		return scheduleRepository.checkHasSchedule(movieID);
	}
	
	@Override
	public List<Schedule> checkScheduleByRoomId(Long roomId) {
		return scheduleRepository.checkScheduleByRoomId(roomId);
	}
}
