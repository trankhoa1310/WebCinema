package com.vn.cines_start.Service;

import com.vn.cines_start.Model.Movie;
import com.vn.cines_start.Model.Schedule;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IScheduleService {
    List<Schedule> findAll();

    Optional<Schedule> findScheduleById(Long id);
    Schedule create(Schedule schedule);

    Schedule edit(Schedule schedule);

    void delete(Long id);
    
    List<Schedule> findScheduleByShowTime(LocalDate showTime);
    
    List<Time> findAllByShowTime(Long movieId, Date showDate);

	List<Date> findScheduleByMovieId(Long movieId);
	
	List<Long> findAllByShowDate(Date showDate);
	
	List<Schedule> findAllByShowDate1(Date showDate);
	
	List<Schedule> findAllScheduleByMovieId1(Long movieId);
	
	Long findRoomByMovieAndDateTime(Long movieId, Date showDate, Time showTime);
	
	
	List<Time> showTimeByMovieAndRoomDate(Long movieId, Long roomId, Date showDate);
	
	
	Schedule showScheduleID(Long movieId, Long roomId, Date showDate, Time showTime);
	
	List<Schedule> findEndTime(Long roomId, Date showDate);
	
	List<Time> findEndTime2(Time showTime , int duration);
	
	Long findMovieBySchedule(Long scheduleID);
	List<Long> checkHasSchedule(Long movieID);
	List<Schedule> checkScheduleByRoomId(Long roomId);
}
