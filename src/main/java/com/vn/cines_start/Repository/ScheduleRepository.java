package com.vn.cines_start.Repository;

import com.vn.cines_start.Model.Movie;
import com.vn.cines_start.Model.Schedule;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

	List<Schedule> findAllByShowTime(LocalDate showTime);

	
	@Query(value = "select s.show_Time from Schedules s where s.movie_id =?1 and s.show_Date=?2", nativeQuery = true)
	List<Time> findAllByShowTime(Long movieId, Date showDate);
	
	@Query(value = "select s.show_Date from Schedules s where s.movie_id = ?1 group by s.show_Date", nativeQuery = true)
	List<Date> findAllByMovieId(Long movieId);
	
	@Query(value = "select s.movie_id from Schedules s where s.show_Date = ?1 group by s.movie_id", nativeQuery = true)
	List<Long> findAllByShowDate(Date showDate);
	
	@Query(value = "select * from Schedules s where s.show_Date = ?1 ", nativeQuery = true)
	List<Schedule> findAllByShowDate1(Date showDate);
	
	@Query(value = "select * from Schedules s where s.movie_id = ?1", nativeQuery = true)
	List<Schedule> findAllScheduleByMovieId1(Long movieId);
	
	@Query(value = "select s.room_id from Schedules s where s.movie_id =?1 and s.show_Date=?2 and s.show_Time=?3", nativeQuery = true)
	Long findRoomByMovieAndDateTime(Long movieId, Date showDate, Time showTime);
	
	
	@Query(value = "select s.showTime from Schedules s where s.movie_id =?1 and s.room_id=?2 and s.show_Date=?3", nativeQuery = true)
	List<Time> showTimeByMovieAndRoomDate(Long movieId, Long roomId, Date showDate);
	
	@Query(value = "select * from Schedules s where s.movie_id =?1 and s.room_id=?2 and s.show_Date=?3 and s.show_Time=?4", nativeQuery = true)
	Schedule showScheduleID(Long movieId, Long roomId, Date showDate, Time showTime);

	
	@Query(value = "select *  from schedules as sc join movies as m on sc.movie_id = m.movie_id  where sc.room_id=?1 and sc.show_Date=?2", nativeQuery = true)
	List<Schedule> findEndTime(Long roomId, Date showDate);
	
	@Query(value = "SELECT ADDTIME(?1, ?2) Time;", nativeQuery = true)
	List<Time> findEndTime2(Time showTime , int duration);
	
	
	@Query(value = "select s.movie_id from Schedules s where s.id = ?1 ", nativeQuery = true)
	Long findMovieBySchedule(Long scheduleID);
	
	@Query(value = "select s.id from Schedules s where s.movie_id = ?1 ", nativeQuery = true)
	List<Long> checkHasSchedule(Long movieID);
	
	@Query(value = "select * from Schedules s where s.room_id = ?1 ", nativeQuery = true)
	List<Schedule> checkScheduleByRoomId(Long roomId);
}
