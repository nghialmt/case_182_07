

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(16) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `mobile` varchar(13) NOT NULL,
  `DOB` datetime NOT NULL,
  `gender` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user` (`id`, `email`, `password`, `salt`, `name`, `address`, `mobile`, `DOB`, `gender`) VALUES
(1, 'francenghia@gmail.com', 'd85bb34fecb61fc7e17113c7352f49fdcdfd2285a2034c9d2a20d4e0ebddf6d59fe0db9737555e5b162fa7a629374b0c485c', '0c088655faffb569', 'Nghia', 'An Giang', '0942872954', '2019-06-04 00:00:00', 'nam');




ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;
