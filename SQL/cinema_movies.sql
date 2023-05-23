-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'Kevin Lewis','Kinh Dị ','Câu chuyện bắt đầu khi Elly được gia đình của một người bạn nhờ chăm sóc một người phụ nữ lớn tuổi sống trong một căn nhà gỗ hẻo lánh trong vài ngày. Nhận lời đồng ý, nhưng sau đó Elly phát hiện ra sự xuất hiện của một con quỷ đang ẩn náu trong người phụ nữ chỉ chực chờ để thoát ra. Cùng lúc đó, những bí ẩn về cái chết của mẹ cô dần dần được gợi mở bởi những cơn ác mộng mà Elly phải trải qua.','Sarah Grey, Meg Foster, Sarah Dumont',1,'/img/con-thinh-no-tu-coi-am.jpg','CƠN THỊNH NỘ TỪ CÕI ÂM','Holywood','2023-05-27',_binary '',_binary '\0'),(3,'Teng Terdteng','Hài, Kinh Dị','Dựa trên truyền thuyết về nàng Nak, không xuất hiện trong lịch sử - Tid Noi là câu chuyện khai thác về một nhân vật mới trong mối quan hệ giữa Pee Mak và Mae Nak. Khác với suy đoán về một chuyện tình tay ba ngang trái, Tid Noi đơn thuần là anh bạn hàng xóm mang trong mình tình yêu đơn phương thuần khiết dành cho cô gái nhà bên là Nak. Tuy sau này Nak tìm được tình yêu đích thực của đời mình là Mak thì Tid Noi vẫn sẵn sàng ủng hộ và tôn trọng quyết định của Nak, vì lý tưởng duy nhất mà Tid Noi theo đuổi là \"Hạnh phúc của Nak\". Dòng sự kiện về Mak và Nak vẫn diễn ra theo đúng như những gì chúng ta từng biết rằng họ nảy sinh tình cảm và cùng nhau xây dựng gia đình nhưng khai thác ở một khía cạnh mới, Tid Noi xuất hiện như một người bạn đồng hành cùng vợ chồng Mak để giúp đỡ và chăm sóc Nak dưới sự gửi gắm của Mak khi anh ra trận. Sau nhiều biến cố, Tid Noi cho thấy vai trò của mình trong câu chuyện về \"Tình Người Duyên Ma\" một cách nhân văn và mang đến những cách nhìn nhận mới trong tình yêu đơn phương và tình bạn chí cốt. Bởi đôi khi, giúp người mình thương hạnh phúc cũng là một tình yêu chân chính.','Teng Terdteng, Patchrapa Chaichua, Ananda Everingham, Rusameekae Fagerlund, Nataya Chanrung, Somchai Kemglad, Napapa Tantrakul Thời lượng : 1 giờ 44 phút',104,'/img/tinh-nguoi-duyen-ma-ngoai-truyen (1).jpg',' TÌNH NGƯỜI DUYÊN MA: NGOẠI TRUYỆN','Thailand','2023-05-05',_binary '',_binary ''),(4,'Vũ Ngọc Đãng','Hài','Lấy cảm hứng từ web drama Chuyện Xóm Tui, phiên bản điện ảnh sẽ mang một màu sắc hoàn toàn khác: hài hước hơn, gần gũi và nhiều cảm xúc hơn Bộ phim là câu chuyện của Nhót - người phụ nữ “chưa kịp già” đã sắp bị mãn kinh, vội vàng đi tìm chồng. Nhưng sâu thẳm trong cô, là khao khát muốn có một đứa con và luôn muốn hàn gắn với người cha suốt ngày say xỉn của mình.','Thái Hòa, Thu Trang, Tiến Luật, NSND Hồng Vân, Huỳnh Phương, Vinh Râu, Thái Vũ,..',112,'/img/con-nhot-mot-chong.jpg','CON NHÓT MÓT CHỒNG','Vietnam','2023-04-20',_binary '',_binary ''),(5,'James Gunn','Siêu Anh Hùng','Cho dù vũ trụ này có bao la đến đâu, các Vệ Binh của chúng ta cũng không thể trốn chạy mãi mãi. Vệ Binh Dải Ngân Hà 3 dự kiến khởi chiếu tại rạp từ 03.05.2023.','Chris Pratt, Zoe Saldana, Dave Bautista',150,'/img/ve-binh-dai-ngan-ha-3.jpg','VỆ BINH DẢI NGÂN HÀ 3','Holywood','2023-05-06',_binary '',_binary ''),(6,'Louis Leterrier','Hành Động, Hồi Hộp, Phiêu Lưu','Dom Toretto và gia đình của anh ấy bị trở thành mục tiêu của người con trai đầy thù hận của ông trùm ma túy Hernan Reyes.','Vin Diesel, Jason Momoa, Brie Larson,…',90,'/img/fast-X.jpg','FAST AND FURIOUS X','Holywood','2023-05-23',_binary '',_binary ''),(7,'Jalmari Helander','Hành Động','Câu chuyện bắt đầu khi Elly được gia đình của một người bạn nhờ chăm sóc một người phụ nữ lớn tuổi sống trong một căn nhà gỗ hẻo lánh trong vài ngày. Nhận lời đồng ý, nhưng sau đó Elly phát hiện ra sự xuất hiện của một con quỷ đang ẩn náu trong người phụ nữ chỉ chực chờ để thoát ra. Cùng lúc đó, những bí ẩn về cái chết của mẹ cô dần dần được gợi mở bởi những cơn ác mộng mà Elly phải trải qua.','Sarah Grey, Meg Foster, Sarah Dumont',90,'/img/sisu.jpg','SISU - GIÀ GÂN BÁO THÙ','Holywood','2023-05-11',_binary '\0',_binary ''),(16,'ly hai, tran dang khoa','Hài, Kinh Dị','Dựa trên truyền thuyết về nàng Nak, không xuất hiện trong lịch sử - Tid Noi là câu chuyện khai thác về một nhân vật mới trong mối quan hệ giữa Pee Mak và Mae Nak. Khác với suy đoán về một chuyện tình tay ba ngang trái, Tid Noi đơn thuần là anh bạn hàng xóm mang trong mình tình yêu đơn phương thuần khiết dành cho cô gái nhà bên là Nak. Tuy sau này Nak tìm được tình yêu đích thực của đời mình là Mak thì Tid Noi vẫn sẵn sàng ủng hộ và tôn trọng quyết định của Nak, vì lý tưởng duy nhất mà Tid Noi theo đuổi là \"Hạnh phúc của Nak\". Dòng sự kiện về Mak và Nak vẫn diễn ra theo đúng như những gì chúng ta từng biết rằng họ nảy sinh tình cảm và cùng nhau xây dựng gia đình nhưng khai thác ở một khía cạnh mới, Tid Noi xuất hiện như một người bạn đồng hành cùng vợ chồng Mak để giúp đỡ và chăm sóc Nak dưới sự gửi gắm của Mak khi anh ra trận. Sau nhiều biến cố, Tid Noi cho thấy vai trò của mình trong câu chuyện về \"Tình Người Duyên Ma\" một cách nhân văn và mang đến những cách nhìn nhận mới trong tình yêu đơn phương và tình b','Sarah Grey, Meg Foster, Sarah Dumont',160,'/img/trans.jpg','AGGG','Holywood','2023-05-17',_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-20 18:00:47
