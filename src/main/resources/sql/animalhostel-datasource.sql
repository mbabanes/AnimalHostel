SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `animalhostel`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `animals`
--

CREATE TABLE `animals` (
  `id_animal` int(6) NOT NULL,
  `name` varchar(20) COLLATE utf8_polish_ci DEFAULT NULL,
  `id_animal_type` int(3) DEFAULT NULL,
  `color` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `weight` int(4) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `date_of_register` date DEFAULT NULL,
  `id_worker` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `animals`
--

INSERT INTO `animals` (`id_animal`, `name`, `id_animal_type`, `color`, `weight`, `birthday`, `date_of_register`, `id_worker`) VALUES
(1, 'Burek', 1, 'czarny', 20, '2017-08-01', '2017-08-02', 1),
(18, 'AZOR', 9, 'BIAŁO-BRĄZOWY', 50, '2017-08-03', '2017-08-25', 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `animals_in_slots`
--

CREATE TABLE `animals_in_slots` (
  `id_animal_in_slot` int(4) NOT NULL,
  `id_animal` int(6) DEFAULT NULL,
  `id_slot_animal` int(4) DEFAULT NULL,
  `date_of_in` date DEFAULT NULL,
  `date_of_out` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `animals_in_slots`
--

INSERT INTO `animals_in_slots` (`id_animal_in_slot`, `id_animal`, `id_slot_animal`, `date_of_in`, `date_of_out`) VALUES
(1, 1, 1, '2017-08-01', NULL),
(4, 18, 3, '2017-08-25', NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `animal_to_heal`
--

CREATE TABLE `animal_to_heal` (
  `id_animal_to_heal` int(5) NOT NULL,
  `id_animal` int(6) DEFAULT NULL,
  `date_of_register` date DEFAULT NULL,
  `symptoms` text COLLATE utf8_polish_ci,
  `done` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `animal_to_heal`
--

INSERT INTO `animal_to_heal` (`id_animal_to_heal`, `id_animal`, `date_of_register`, `symptoms`, `done`) VALUES
(1, 1, '2017-08-08', 'Pies zle sie czuje', 0),
(2, 1, '2017-08-01', 'Katar', 1),
(3, 1, '2017-09-15', 'asd asd asd asd asd', 0),
(4, 1, '2017-09-15', 'Adsdasd', 0),
(5, 1, '2017-09-29', 'Wypadająca sierść', 0),
(6, 1, '2017-09-29', 'afafasfadfdfdaf aasf faf af', 0),
(7, 18, '2017-09-29', 'Cos tam mu sie dzieje', 0),
(8, 1, '2017-09-29', 'Kaszel', 0),
(9, 1, '2017-10-01', 'adasdasd', 0),
(10, 1, '2017-10-01', 'Asdwqwe', 0),
(11, 1, '2017-10-01', 'dasd qweqw asd\nqeqwe', 0),
(12, 1, '2017-10-02', 'asdasdd', 0),
(13, 1, '2017-11-07', 'asdasfdafgsddfg', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `animal_type`
--

CREATE TABLE `animal_type` (
  `id_animal_type` int(3) NOT NULL,
  `type` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL,
  `race` varchar(50) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `animal_type`
--

INSERT INTO `animal_type` (`id_animal_type`, `type`, `race`) VALUES
(1, 'pies', 'owczarek niemiecki'),
(9, 'Pies', 'Bernardyn'),
(11, 'Kot', 'Pers'),
(12, 'Pies', 'Buldog'),
(15, 'KOT', 'DACHOWIEC'),
(19, 'PIES', 'RATLEREK'),
(21, 'PIES', 'LABRADOR');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `job_position`
--

CREATE TABLE `job_position` (
  `id_job_position` int(1) NOT NULL,
  `job_position_name` varchar(60) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `job_position`
--

INSERT INTO `job_position` (`id_job_position`, `job_position_name`) VALUES
(1, 'Opiekun zwierząt');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `slots_for_animal`
--

CREATE TABLE `slots_for_animal` (
  `id_slot_animal` int(4) NOT NULL,
  `free` tinyint(1) DEFAULT NULL,
  `date_of_open` date DEFAULT NULL,
  `id_animal_type` int(3) DEFAULT NULL,
  `area` decimal(7,2) DEFAULT NULL,
  `inside` tinyint(1) DEFAULT NULL,
  `heigth` decimal(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `slots_for_animal`
--

INSERT INTO `slots_for_animal` (`id_slot_animal`, `free`, `date_of_open`, `id_animal_type`, `area`, `inside`, `heigth`) VALUES
(1, 0, '2017-08-01', 1, '20.00', 1, '2.00'),
(2, 1, '2017-08-01', 1, '20.00', 1, '3.00'),
(3, 0, '2017-08-02', 9, '12.00', 1, '2.00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `worker`
--

CREATE TABLE `worker` (
  `id_worker` int(4) NOT NULL,
  `name` varchar(150) COLLATE utf8_polish_ci DEFAULT NULL,
  `surname` varchar(150) COLLATE utf8_polish_ci DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `id_job_position` int(1) DEFAULT NULL,
  `email` varchar(150) COLLATE utf8_polish_ci DEFAULT NULL,
  `password` varchar(200) COLLATE utf8_polish_ci DEFAULT NULL,
  `date_of_employm` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `worker`
--

INSERT INTO `worker` (`id_worker`, `name`, `surname`, `salary`, `id_job_position`, `email`, `password`, `date_of_employm`) VALUES
(1, 'Jan', 'Kowalski', '2300.00', 1, 'as@as.pl', '1234', '2017-08-02'),
(2, 'Kuba', 'Wiej', '2000.00', 1, 'asd@wop.pl', 'asd', '2017-08-01');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `worker_data`
--

CREATE TABLE `worker_data` (
  `id_worker` int(4) NOT NULL,
  `PESEL` varchar(10) COLLATE utf8_polish_ci DEFAULT NULL,
  `street` varchar(150) COLLATE utf8_polish_ci DEFAULT NULL,
  `house_number` varchar(4) COLLATE utf8_polish_ci DEFAULT NULL,
  `flat_number` varchar(4) COLLATE utf8_polish_ci DEFAULT NULL,
  `city` varchar(150) COLLATE utf8_polish_ci DEFAULT NULL,
  `post_code` varchar(7) COLLATE utf8_polish_ci DEFAULT NULL,
  `post` varchar(150) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `animals`
--
ALTER TABLE `animals`
  ADD PRIMARY KEY (`id_animal`),
  ADD KEY `Animals__animal_type` (`id_animal_type`),
  ADD KEY `Animals__worker` (`id_worker`);

--
-- Indexes for table `animals_in_slots`
--
ALTER TABLE `animals_in_slots`
  ADD PRIMARY KEY (`id_animal_in_slot`),
  ADD KEY `animals_in_slots__slots_for_animal` (`id_slot_animal`),
  ADD KEY `animals_in_slots__animals` (`id_animal`);

--
-- Indexes for table `animal_to_heal`
--
ALTER TABLE `animal_to_heal`
  ADD PRIMARY KEY (`id_animal_to_heal`),
  ADD KEY `animal_to_heal__animals` (`id_animal`);

--
-- Indexes for table `animal_type`
--
ALTER TABLE `animal_type`
  ADD PRIMARY KEY (`id_animal_type`),
  ADD UNIQUE KEY `race` (`race`);
ALTER TABLE `animal_type` ADD FULLTEXT KEY `type` (`type`);

--
-- Indexes for table `job_position`
--
ALTER TABLE `job_position`
  ADD PRIMARY KEY (`id_job_position`);

--
-- Indexes for table `slots_for_animal`
--
ALTER TABLE `slots_for_animal`
  ADD PRIMARY KEY (`id_slot_animal`),
  ADD KEY `slot_types__animal_types` (`id_animal_type`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`id_worker`),
  ADD KEY `worker__job_position` (`id_job_position`);

--
-- Indexes for table `worker_data`
--
ALTER TABLE `worker_data`
  ADD PRIMARY KEY (`id_worker`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `animals`
--
ALTER TABLE `animals`
  MODIFY `id_animal` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT dla tabeli `animals_in_slots`
--
ALTER TABLE `animals_in_slots`
  MODIFY `id_animal_in_slot` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT dla tabeli `animal_to_heal`
--
ALTER TABLE `animal_to_heal`
  MODIFY `id_animal_to_heal` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT dla tabeli `animal_type`
--
ALTER TABLE `animal_type`
  MODIFY `id_animal_type` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT dla tabeli `job_position`
--
ALTER TABLE `job_position`
  MODIFY `id_job_position` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT dla tabeli `slots_for_animal`
--
ALTER TABLE `slots_for_animal`
  MODIFY `id_slot_animal` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `worker`
--
ALTER TABLE `worker`
  MODIFY `id_worker` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `animals`
--
ALTER TABLE `animals`
  ADD CONSTRAINT `Animals__animal_type` FOREIGN KEY (`id_animal_type`) REFERENCES `animal_type` (`id_animal_type`),
  ADD CONSTRAINT `Animals__worker` FOREIGN KEY (`id_worker`) REFERENCES `worker` (`id_worker`);

--
-- Ograniczenia dla tabeli `animals_in_slots`
--
ALTER TABLE `animals_in_slots`
  ADD CONSTRAINT `animals_in_slots__animals` FOREIGN KEY (`id_animal`) REFERENCES `animals` (`id_animal`),
  ADD CONSTRAINT `animals_in_slots__slots_for_animal` FOREIGN KEY (`id_slot_animal`) REFERENCES `slots_for_animal` (`id_slot_animal`);

--
-- Ograniczenia dla tabeli `animal_to_heal`
--
ALTER TABLE `animal_to_heal`
  ADD CONSTRAINT `animal_to_heal__animals` FOREIGN KEY (`id_animal`) REFERENCES `animals` (`id_animal`);

--
-- Ograniczenia dla tabeli `slots_for_animal`
--
ALTER TABLE `slots_for_animal`
  ADD CONSTRAINT `slot_types__animal_types` FOREIGN KEY (`id_animal_type`) REFERENCES `animal_type` (`id_animal_type`);

--
-- Ograniczenia dla tabeli `worker`
--
ALTER TABLE `worker`
  ADD CONSTRAINT `worker__job_position` FOREIGN KEY (`id_job_position`) REFERENCES `job_position` (`id_job_position`);

--
-- Ograniczenia dla tabeli `worker_data`
--
ALTER TABLE `worker_data`
  ADD CONSTRAINT `worker__worker_data` FOREIGN KEY (`id_worker`) REFERENCES `worker` (`id_worker`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
