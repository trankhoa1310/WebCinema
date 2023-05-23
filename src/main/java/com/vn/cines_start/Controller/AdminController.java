package com.vn.cines_start.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vn.cines_start.DTO.ScheduleDTO;
import com.vn.cines_start.DTO.SeatDTO;
import com.vn.cines_start.DTO.UserDTO;
import com.vn.cines_start.Model.Movie;
import com.vn.cines_start.Model.Role;
import com.vn.cines_start.Model.Room;
import com.vn.cines_start.Model.Schedule;
import com.vn.cines_start.Model.Seat;
import com.vn.cines_start.Model.Ticket;
import com.vn.cines_start.Model.User;
import com.vn.cines_start.Service.Iplm.BillUserImplement;
import com.vn.cines_start.Service.Iplm.MovieImplement;
import com.vn.cines_start.Service.Iplm.RoleServiceImpl;
import com.vn.cines_start.Service.Iplm.RoomImplement;
import com.vn.cines_start.Service.Iplm.ScheduleImplement;
import com.vn.cines_start.Service.Iplm.SeatImplement;
import com.vn.cines_start.Service.Iplm.TicketImplement;
import com.vn.cines_start.Service.Iplm.UserImplement;

@Controller
public class AdminController {
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/img";
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SeatImplement seatImplement;
	@Autowired
	private UserImplement userImplement;

	@Autowired
	private RoomImplement roomImplement;

	@Autowired
	private MovieImplement movieImplement;

	@Autowired
	private ScheduleImplement scheduleImplement;

	@Autowired
	private TicketImplement ticketImplement;
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	
	@Autowired
	private BillUserImplement billUserImplement;

	@GetMapping("/admin")
	public String adminHome(Model model) {
		/* model.addAttribute("users", userImplement.findAll()); */
		// model.addAttribute("roles", roleService.getAllRole());
		List<User> userList = userImplement.findUserByRole(2L);
		List<User> userList2 = new ArrayList<>();
		for (User user : userList) {
			if(user.getRoles().size() == 1)
			{
				userList2.add(user);
			}
			
		}
		model.addAttribute("users", userList2);
		return "user";
	}

	/* User */
	@GetMapping("/admin/users")
	public String getAcc(Model model) {
		List<User> userList = userImplement.findUserByRole(2L);
		List<User> userList2 = new ArrayList<>();
		for (User user : userList) {
			if(user.getRoles().size() == 1)
			{
				userList2.add(user);
			}
			
		}
		model.addAttribute("users", userList2);
		// model.addAttribute("roles", roleService.getAllRole());
		return "user";
	}

	@GetMapping("/admin/users/add")
	public String getUserAdd(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		model.addAttribute("roles", roleServiceImpl.getAllRole());
		return "addUsers";
	}

	@PostMapping("/admin/users/add")
	public String postUserAdd(@ModelAttribute("userDTO") UserDTO userDTO, RedirectAttributes attributes) {
		// convert dto > entity
		User user = new User();
		user.setId(userDTO.getId());
		user.setEmail(userDTO.getEmail());
		User check=userImplement.findAllByEmail(userDTO.getEmail());		
		String password = userDTO.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPhone(userDTO.getPhone());
		List<Role> roles = new ArrayList<>();
		for (Long item : userDTO.getRoleIds()) {
			roles.add(roleServiceImpl.findRoleById(item).get());
		}
		user.setRoles(roles);
		if(check==null) {
		userImplement.create(user);}
		else {
			attributes.addFlashAttribute("Fail", "Thêm thất bại");
			return "redirect:/admin/users";
		}
		return "redirect:/admin/users";
	}
	@PostMapping("/admin/users/Edit")
	public String postUserEdit(@ModelAttribute("userDTO") UserDTO userDTO, RedirectAttributes attributes) {
		// convert dto > entity
		User user = new User();
		user.setId(userDTO.getId());
		user.setEmail(userDTO.getEmail());
		User check=userImplement.findAllByEmail(userDTO.getEmail());		
		String password = userDTO.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPhone(userDTO.getPhone());
		/*
		 * List<Role> roles = new ArrayList<>(); for (Long item : userDTO.getRoleIds())
		 * { roles.add(roleServiceImpl.findRoleById(item).get()); }
		 * user.setRoles(roles);
		 */
		List<Role> roles = new ArrayList<>();
		roles.add(roleServiceImpl.findRoleById(2L).get());
		user.setRoles(roles);
		
		
		userImplement.create(user);
		return "redirect:/admin/users";
	}

	@GetMapping("/admin/users/delete")
	public String deleteUser(@RequestParam("id") Long id) {
		userImplement.delete(id);
		return "redirect:/admin/users";
	}// delete 1 user

	@GetMapping("/admin/users/update")
	public String updateUser(@RequestParam("id") Long id, Model model, RedirectAttributes attributes) {
		Optional<User> opUser = userImplement.findUserById(id);
		if (opUser.isPresent()) {
			User user = opUser.get();
			// convert entity > dto
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setEmail(user.getEmail());
			userDTO.setPassword("");
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setPhone(user.getPhone());
			List<Long> roleIds = new ArrayList<>();
			for (Role item : user.getRoles()) {
				roleIds.add(item.getId());
			}
			userDTO.setRoleIds(roleIds);
			model.addAttribute("userDTO", userDTO);
			model.addAttribute("roles", roleServiceImpl.getAllRole());
			return "addUser";
		} else {
			return "404";
		}

	}

	/* User */

	/* Room */

	@GetMapping("/admin/room")
	public String indexRoom(Model model) {
		List<Room> rooms = roomImplement.findAll();
		model.addAttribute("rooms", rooms);
		return "room";
	}

	@GetMapping("/admin/addRoom")
	public String addRoom(Model model) {
		model.addAttribute("room", new Room());
		return "addRoom";
	}

	@GetMapping("/admin/editRoom")
	public String editRoom(@RequestParam("id") Long roomId, Model model) {
		Optional<Room> roomEdit = roomImplement.findRoomById(roomId);
		roomEdit.ifPresent(room -> model.addAttribute("room", room));
		return "editRoom";
	}
	

	@PostMapping("/admin/saveRoom")
	public String saveRoom(Room room,RedirectAttributes attributes) {
		List<Room> roomList = roomImplement.findAll();
		if(!room.isStatus())
		{
			List<Schedule> Schedules = scheduleImplement.checkScheduleByRoomId(room.getId());
			
			for (Schedule sc : Schedules) {
				if(sc.getRooms().getId() == room.getId())
				{
					attributes.addFlashAttribute("Fail1", "Vui lòng xóa hết lịch chiếu còn liên quan đến phòng");
					return "redirect:/admin/addRoom";
				}
			}
		}
		for (Room room2 : roomList) {
			if(room2.getId()!=room.getId())
			{
				if(room2.getName().equals(room.getName()))
				{
					attributes.addFlashAttribute("Fail2", "Phòng đã trùng tên");
					return "redirect:/admin/addRoom";
				}
			}
		}
		roomImplement.edit(room);
		return "redirect:/admin/room";
	}

	@GetMapping("/admin/deleteRoom")
	public String deleteRoom(@RequestParam("id") Long roomId, Model model) {
		roomImplement.delete(roomId);
		return "redirect:/admin/room";
	}

	/* Room */

	/* Movie */
	@GetMapping("/admin/movie")
	public String indexMovie(Model model) {
		List<Movie> movies = movieImplement.findAll();
		model.addAttribute("movies", movies);
		return "movie";
	}

	@GetMapping("/admin/addMovie")
	public String addMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "addMovie";
	}

	@GetMapping("/admin/editMovie")
	public String editMovie(@RequestParam("id") Long movieId, Model model) {
		Optional<Movie> movieEdit = movieImplement.findMovieById(movieId);
		movieEdit.ifPresent(movie -> model.addAttribute("movie", movie));
		return "addMovie";

	}
	@GetMapping("/admin/setDeleted")
	public String setDeleted(@RequestParam("id")Long movieId,Model model){
		Optional<Movie> set = movieImplement.findMovieById(movieId);

		if (set.isPresent()) {
			Movie mv = set.get();
			if(mv.isDeleted()==true) mv.setDeleted(false);
			else mv.setDeleted(true);
			movieImplement.create(mv);
		}
		return "redirect:/admin/movie";
	}
	@PostMapping("/admin/adminsaveMovie")
	public String saveMovie(Movie movie, @RequestParam("productImage") MultipartFile fileProductImage,
			@RequestParam("imgName") String imgName) throws IOException {
		String imageUUID;
		String imgnew;
		if (!fileProductImage.isEmpty()) {
			imageUUID = fileProductImage.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, fileProductImage.getBytes());
			imgnew = "/img/" + imageUUID;
		} else {
			imageUUID = imgName;
			imgnew = imageUUID;
			/*
			 * movie.setStatus(false); movie.setImageURl("/img/"+imageUUID);
			 * movieImplement.create(movie); return "redirect:/admin/movie";
			 */
		} // save image

		movie.setImageURl(imgnew);

		
		if(!scheduleImplement.findAllScheduleByMovieId1(movie.getId()).isEmpty())
		{
			movie.setStatus(true);
		}
		else {
			movie.setStatus(false);
		}
		
		movieImplement.create(movie);
		return "redirect:/admin/movie";
	}

	/*
	 * @PostMapping("/admin/saveMovie2") public String saveMovie2(Movie movie,
	 * 
	 * @RequestParam("imageMovie")MultipartFile imageMovie, RedirectAttributes
	 * attributes) {
	 * 
	 * try { movieImplement.create(imageMovie,movie); movieImplement.create(movie);
	 * attributes.addFlashAttribute("success","Add successfully!"); } catch
	 * (Exception e) { e.printStackTrace();
	 * attributes.addFlashAttribute("error","Failed to Add!"); } return
	 * "redirect:/admin/movie"; }
	 */
	@GetMapping("/admin/deleteMovie")
	public String deleteMovie(@RequestParam("id") Long movieId, Model model) {
		movieImplement.delete(movieId);
		return "redirect:/admin/movie";
	}
	/* Movie */

	/* Seat */
	@GetMapping("/admin/seat")
	public String indexSeat(Model model) {
		List<Seat> seats = seatImplement.findAll();
		model.addAttribute("seats", seats);
		return "seat";
	}

	@GetMapping("/admin/addSeat")
	public String addSeat(Model model) {
		model.addAttribute("seatDTO", new SeatDTO());
		model.addAttribute("rooms", roomImplement.findAll());
		return "addSeat";
	}

	@GetMapping("/admin/editSeat")
	public String editSeat(Model model) {
		model.addAttribute("seatDTO", new SeatDTO());
		model.addAttribute("rooms", roomImplement.findAll());
		return "editSeat";
	}

	@PostMapping("/admin/addSeat")
	public String postSeatAdd(@ModelAttribute("seatDTO") SeatDTO seatDTO, RedirectAttributes attributes) {
		// convert dto > entity
		Seat seat = new Seat();

		seat.setId(seatDTO.getId());
		seat.setCategoryName(seatDTO.getCategoryName());
		seat.setColumnSeat(seatDTO.getColumnSeat());
		seat.setRowSeat(seatDTO.getRowSeat());
		seat.setPrice(seatDTO.getPrice());
		seat.setStatus(true);
		Optional<Room> roomNew = roomImplement.findRoomById(seatDTO.getRoomIds());
		roomNew.ifPresent(newRoom -> seat.setRooms(newRoom));
		List<Seat> listCount= seatImplement.findAllSeatsByRoomId(seatDTO.getRoomIds());
		
		/*
		 * System.out.println(seatDTO.getRoomIds()); Room k= new Room(); k.setId(1L);
		 * seat.setRooms(k);
		 */
		List<Seat> listSeat = seatImplement.findAll();
		for (Seat seat2 : listSeat) {
					if (seat2.getRooms().getId()==seatDTO.getRoomIds() && seat2.getColumnSeat()==seatDTO.getColumnSeat() && seat2.getRowSeat()==seatDTO.getRowSeat()) {
						attributes.addFlashAttribute("Fail1", "Ghế đã trùng vị trí");
						return "redirect:/admin/addSeat";
				}
					if ( seat2.getRooms().getId()==seatDTO.getRoomIds() && seat2.getCategoryName().equals(seatDTO.getCategoryName())) {
						attributes.addFlashAttribute("Fail2", "Ghế đã trùng tên");
						return "redirect:/admin/addSeat";
				}
					if (listCount.size()>=seat.getRooms().getCapacity()) {
						attributes.addFlashAttribute("Fail3", "Số lượng phòng đã đạt giới hạn");
						return "redirect:/admin/addSeat";
				}
			
		}
		seatImplement.create(seat);
		return "redirect:/admin/seat";
	}
	@PostMapping("/admin/editSeat")
	public String postSeatEdit(@ModelAttribute("seatDTO") SeatDTO seatDTO, RedirectAttributes attributes) {
		// convert dto > entity
		Seat seat = new Seat();

		seat.setId(seatDTO.getId());
		seat.setCategoryName(seatDTO.getCategoryName());
		seat.setColumnSeat(seatDTO.getColumnSeat());
		seat.setRowSeat(seatDTO.getRowSeat());
		seat.setPrice(seatDTO.getPrice());
		seat.setStatus(true);
		Optional<Room> roomNew = roomImplement.findRoomById(seatDTO.getRoomIds());
		roomNew.ifPresent(newRoom -> seat.setRooms(newRoom));
		List<Seat> listCount= seatImplement.findAllSeatsByRoomId(seatDTO.getRoomIds());
		
		/*
		 * System.out.println(seatDTO.getRoomIds()); Room k= new Room(); k.setId(1L);
		 * seat.setRooms(k);
		 */
		List<Seat> listSeat = seatImplement.findAll();
		for (Seat seat2 : listSeat) {
				if(seat2.getId() != seatDTO.getId())	
				{
					
					if (seat2.getRooms().getId()==seatDTO.getRoomIds() && seat2.getColumnSeat()==seatDTO.getColumnSeat() && seat2.getRowSeat()==seatDTO.getRowSeat()) {
						attributes.addFlashAttribute("Fail1", "Ghế đã trùng vị trí");
						return "redirect:/admin/editSeat";
				}
					if ( seat2.getRooms().getId()==seatDTO.getRoomIds() && seat2.getCategoryName().equals(seatDTO.getCategoryName())) {
						System.out.println(seat2.getCategoryName());
						attributes.addFlashAttribute("Fail2", "Ghế đã trùng tên");
						return "redirect:/admin/editSeat";
				}
			}
				
		}
		seatImplement.create(seat);
		return "redirect:/admin/seat";
	}

	@GetMapping("/admin/deleteSeat")
	public String deleteSeat(@RequestParam("id") Long seatId, Model model) {
		seatImplement.delete(seatId);
		return "redirect:/admin/seat";
	}

	@GetMapping("/admin/updateSeat")
	public String updateSeat(@RequestParam("id") Long id, Model model) {
		Optional<Seat> opSeat = seatImplement.findSeatById(id);
		if (opSeat.isPresent()) {
			Seat seat = opSeat.get();
			// convert entity > dto
			SeatDTO seatDTO = new SeatDTO();
			seatDTO.setId(seat.getId());
			seatDTO.setCategoryName(seat.getCategoryName());
			seatDTO.setColumnSeat(seat.getColumnSeat());
			seatDTO.setRowSeat(seat.getRowSeat());
			seatDTO.setPrice(seat.getPrice());
			seatDTO.setStatus(seat.isStatus());

			seatDTO.setRoomIds(seat.getRooms().getId());
			model.addAttribute("seatDTO", seatDTO);
			model.addAttribute("rooms", roomImplement.findAll());
			return "editSeat";
		} else {
			return "404";
		}

	}

	/* Seat */

	/* Schedule */

	@GetMapping("/admin/schedule")
	public String indexSchedule(Model model) {
		List<Schedule> schedules = scheduleImplement.findAll();
		model.addAttribute("schedules", schedules);
		return "schedule";
	}

	@GetMapping("/admin/addSchedule")
	public String addSchedule(Model model) {
		model.addAttribute("scheduleDTO", new ScheduleDTO());
		model.addAttribute("rooms", roomImplement.findAll());
		model.addAttribute("movies", movieImplement.findAll());
		return "addSchedule";
	}

	@GetMapping("/admin/editSchedule")
	public String editSchedule(@RequestParam("id") Long scheduleId, Model model) {
		Optional<Schedule> scheduleEdit = scheduleImplement.findScheduleById(scheduleId);
		scheduleEdit.ifPresent(schedule -> model.addAttribute("schedule", schedule));
		return "editSchedule";
	}

	@PostMapping("/admin/addSchedule")
	public String postScheduleAdd(@ModelAttribute("scheduleDTO") ScheduleDTO scheduleDTO,
			RedirectAttributes attributes) {
		// convert dto > entity
		Date date = Date.valueOf(scheduleDTO.getShowDate());
		Time time = Time.valueOf(scheduleDTO.getShowTime());

		Schedule schedule = new Schedule();
		schedule.setId(scheduleDTO.getId());
		Optional<Room> roomNew = roomImplement.findRoomById(scheduleDTO.getRoomIds());
		roomNew.ifPresent(newRoom -> schedule.setRooms(newRoom));

		Optional<Movie> movieNew = movieImplement.findMovieById(scheduleDTO.getMovieIds());
		movieNew.ifPresent(newMovie -> schedule.setMovies(newMovie));
		movieNew.ifPresent(newMovie -> newMovie.setStatus(true));
		movieNew.ifPresent(newMovie -> movieImplement.create(newMovie));

		schedule.setName(scheduleDTO.getName());

		List<Schedule> schedules = new ArrayList<>();
		schedules = scheduleImplement.findEndTime(schedule.getRooms().getId(), date);
		Long abc = Long.valueOf(schedule.getMovies().getDuration());
		if (schedules != null) {
			for (Schedule s : schedules) {
				if(scheduleDTO.getId() != s.getId())
				{
					if ((scheduleDTO.getShowTime().isAfter(s.getShowTime())
							&& scheduleDTO.getShowTime().isBefore(s.getEndTime()))
							|| (scheduleDTO.getShowTime().plusMinutes(abc).isAfter(s.getShowTime())
									&& scheduleDTO.getShowTime().plusMinutes(abc).isBefore(s.getEndTime()))
							|| (s.getShowTime().isAfter(scheduleDTO.getShowTime())
									&& s.getShowTime().isBefore(scheduleDTO.getShowTime().plusMinutes(abc)))
							||(s.getEndTime().isAfter(scheduleDTO.getShowTime())
									&& s.getEndTime().isBefore(scheduleDTO.getShowTime().plusMinutes(abc)))
							 ) {
						attributes.addFlashAttribute("Fail1", "Trùng lịch");
						return "redirect:/admin/addSchedule";
					}
				}
				
			}
		}
		if(scheduleDTO.getShowTime().plusMinutes(abc).isBefore(scheduleDTO.getShowTime()))
		{
			attributes.addFlashAttribute("Fail2", "Thời gian kết thúc không được vượt quá 23:59");
			return "redirect:/admin/addSchedule";
		}
		schedule.setShowDate(scheduleDTO.getShowDate());
		schedule.setShowTime(scheduleDTO.getShowTime());
		schedule.setStatus(true);

		schedule.setEndTime(scheduleDTO.getShowTime().plusMinutes(abc));

		scheduleImplement.create(schedule);

		/*
		 * schedule.setEndTime(scheduleImplement.findEndTime(abc*60,
		 * scheduleDTO.getMovieIds(), scheduleDTO.getRoomIds(), date,time));
		 */

		return "redirect:/admin/schedule";
	}

	@GetMapping("/admin/deleteSchedule")
	public String deleteSchedule(@RequestParam("id") Long scheduleId, Model model) {

		Optional<Movie> movieNew = movieImplement.findMovieById(scheduleImplement.findMovieBySchedule(scheduleId));
		scheduleImplement.delete(scheduleId);
		Movie movieCheck = movieNew.get();
		List<Long> lstMovie = scheduleImplement.checkHasSchedule(movieCheck.getId());
		if (lstMovie.isEmpty()) {
			movieNew.ifPresent(newMovie -> newMovie.setStatus(false));
			movieNew.ifPresent(newMovie -> movieImplement.create(newMovie));
		}

		return "redirect:/admin/schedule";
	}

	@GetMapping("/admin/updateSchedule")
	public String updateSchedule(@RequestParam("id") Long id, Model model) {
		Optional<Schedule> opSchedule = scheduleImplement.findScheduleById(id);
		if (opSchedule.isPresent()) {

			Schedule schedule = opSchedule.get();
			// convert entity > dto
			ScheduleDTO scheduleDTO = new ScheduleDTO();

			scheduleDTO.setId(schedule.getId());
			scheduleDTO.setName(schedule.getName());

			scheduleDTO.setShowDate(schedule.getShowDate());
			scheduleDTO.setShowTime(schedule.getShowTime());
			scheduleDTO.setStatus(true);

			scheduleDTO.setRoomIds(schedule.getRooms().getId());
			scheduleDTO.setMovieIds(schedule.getMovies().getId());

			model.addAttribute("scheduleDTO", scheduleDTO);

			model.addAttribute("rooms", roomImplement.findAll());
			model.addAttribute("movies", movieImplement.findAll());
			return "addSchedule";
		} else {
			return "404";
		}

	}

	/* Schedule */

	/* BILL */

	@GetMapping("/admin/bill")
	public String billUser(Model model) {
		List<Ticket> tickets = ticketImplement.findAll();
		model.addAttribute("tickets", tickets);
		return "hoadon";
	}

	/* BILL */
}
