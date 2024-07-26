CREATE TABLE `form` (
                        `form_id` bigint NOT NULL AUTO_INCREMENT,
                        `name` varchar(50) NULL,
                        `description` text NULL,
                        PRIMARY KEY (`form_id`)
);

CREATE TABLE `question` (
                            `question_id` bigint NOT NULL AUTO_INCREMENT,
                            `form_id` bigint NOT NULL,
                            `name` varchar(255) NULL,
                            `description` text NULL,
                            `input_types` varchar(20) NULL,
                            `required` boolean NULL,
                            PRIMARY KEY (`question_id`),
                            FOREIGN KEY (`form_id`) REFERENCES `form` (`form_id`)
);

CREATE TABLE `option` (
                          `option_id` bigint NOT NULL AUTO_INCREMENT,
                          `question_id` bigint NOT NULL,
                          `content` varchar(50) NULL,
                          `seq` int NULL,
                          PRIMARY KEY (`option_id`),
                          FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
);

CREATE TABLE `feedback` (
                            `feedback_id` bigint NOT NULL AUTO_INCREMENT,
                            `feedback_at` datetime NULL,
                            `form_id` bigint NOT NULL,
                            PRIMARY KEY (`feedback_id`),
                            FOREIGN KEY (`form_id`) REFERENCES `form` (`form_id`)
);

CREATE TABLE `answer` (
                          `answer_id` bigint NOT NULL AUTO_INCREMENT,
                          `feedback_id` bigint NOT NULL,
                          `question_id` bigint NOT NULL,
                          `content` varchar(255) NULL,
                          PRIMARY KEY (`answer_id`),
                          FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`feedback_id`),
                          FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
);

CREATE TABLE `feedback_history` (
                                    `feedback_history_id` bigint NOT NULL AUTO_INCREMENT,
                                    `form_id` bigint NOT NULL,
                                    `questions` text NULL,
                                    `answers` text NULL,
                                    `created_at` datetime NULL,
                                    PRIMARY KEY (`feedback_history_id`),
                                    FOREIGN KEY (`form_id`) REFERENCES `form` (`form_id`)
);
