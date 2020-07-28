-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 28, 2020 lúc 11:06 AM
-- Phiên bản máy phục vụ: 10.1.32-MariaDB
-- Phiên bản PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shophoa`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoa`
--

CREATE TABLE `hoa` (
  `id` int(11) NOT NULL,
  `ten_hoa` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `so_luong` int(11) NOT NULL,
  `gia_ban` float NOT NULL,
  `hinh_anh` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mo_ta` text COLLATE utf8_unicode_ci NOT NULL,
  `id_loaihoa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoa`
--

INSERT INTO `hoa` (`id`, `ten_hoa`, `so_luong`, `gia_ban`, `hinh_anh`, `mo_ta`, `id_loaihoa`) VALUES
(1, 'Hoa Hồng', 1, 10000, 'img1_1595927013642.jpg', '', 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaihoa`
--

CREATE TABLE `loaihoa` (
  `id` int(11) NOT NULL,
  `ten_loaihoa` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaihoa`
--

INSERT INTO `loaihoa` (`id`, `ten_loaihoa`) VALUES
(1, 'Hoa Kỷ Niệm'),
(2, 'Hoa Chúc Mừng'),
(3, 'Hoa Cưới'),
(4, 'Hoa Hạnh Phúc'),
(5, 'Hoa Cô Đơn');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hoa`
--
ALTER TABLE `hoa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_loaihoa` (`id_loaihoa`);

--
-- Chỉ mục cho bảng `loaihoa`
--
ALTER TABLE `loaihoa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoa`
--
ALTER TABLE `hoa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `loaihoa`
--
ALTER TABLE `loaihoa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hoa`
--
ALTER TABLE `hoa`
  ADD CONSTRAINT `hoa_ibfk_1` FOREIGN KEY (`id_loaihoa`) REFERENCES `loaihoa` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
