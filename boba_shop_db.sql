CREATE TABLE `users` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `userId` VARCHAR(20),
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(20),
  `address` VARCHAR(200),
  `email` VARCHAR(200),
  `role` TINYINT,
  `created_at` DATETIME,
  `updated_at` DATETIME
);

CREATE TABLE `categories` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(20),
  `description` VARCHAR(100)
);

CREATE TABLE `products` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(20),
  `category` INT(11),
  `cost` DECIMAL(10,2),
  `price` DECIMAL(10,2)
);

CREATE TABLE `product_options` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `product_id` INT(11) NOT NULL,
  `order_id` INT(11) NOT NULL,
  `option_name` VARCHAR(20),
  `quantity` INT(11)
);

CREATE TABLE `orders` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `delivery_id` INT(11),
  `total_amount` DECIMAL(10,2),
  `status` TINYINT,
  `created_at` DATETIME,
  `updated_at` DATETIME,
  `discount_id` INT(11)
);

CREATE TABLE `deliveries` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `order_id` INT(11) NOT NULL,
  `address` VARCHAR(200),
  `courier` INT(11),
  `tracking_id` INT(11),
  `status` TINYINT,
  `created_at` DATETIME,
  `updated_at` DATETIME
);

CREATE TABLE `discounts` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `code` VARCHAR(20) NOT NULL,
  `type` VARCHAR(100),
  `value` INT(11),
  `created_at` DATETIME,
  `expired_at` DATETIME,
  `status` TINYINT
);

ALTER TABLE `products` ADD FOREIGN KEY (`category`) REFERENCES `categories` (`id`);

ALTER TABLE `product_options` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`delivery_id`) REFERENCES `deliveries` (`order_id`);

ALTER TABLE `product_options` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `discounts` ADD FOREIGN KEY (`id`) REFERENCES `orders` (`discount_id`);
