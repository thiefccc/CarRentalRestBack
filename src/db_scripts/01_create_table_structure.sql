create schema car_rental_db collate cp1251_general_ci;

create table rental_offices
(
	rental_office_id int auto_increment
		primary key,
	rental_office_name varchar(150) not null,
	rental_office_address varchar(300) null
)
comment 'Places where renters could get of return items.';

create table rented_items
(
	rented_item_id int auto_increment
		primary key,
	rented_item_name varchar(150) not null,
	rented_item_type int not null,
	car_model varchar(100) null,
	car_plate_number varchar(50) null,
	is_rented bit default b'1' not null
)
comment 'Items which renters get and return in rental offices';

create table renters
(
	renter_id int auto_increment
		primary key,
	renter_name varchar(300) not null,
	renter_info varchar(255) null
)
comment 'Table for persons who rents items';

create table rents
(
	rent_id int auto_increment
		primary key,
	renter_id int not null,
	rented_item_id int not null,
	get_rental_office_id int not null,
	get_timestamp datetime not null,
	return_rental_office_id int null,
	return_timestamp datetime null,
	expiration_date datetime null,
	rent_info varchar(1000) null,
	constraint rents_rental_offices_get_fk
		foreign key (get_rental_office_id) references rental_offices (rental_office_id),
	constraint rents_rental_offices_ret_fk
		foreign key (return_rental_office_id) references rental_offices (rental_office_id),
	constraint rents_rented_items_fk
		foreign key (rented_item_id) references rented_items (rented_item_id),
	constraint rents_renters_renter_id_fk
		foreign key (renter_id) references renters (renter_id)
)
comment 'Renters'' rents of items in rental offices.';

