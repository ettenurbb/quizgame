-- Очистка таблиц перед заполнением (по желанию)
TRUNCATE TABLE answers, questions RESTART IDENTITY;

-- Вопросы лёгкого уровня (difficulty = 1)
INSERT INTO questions (text, difficulty) VALUES
('Какая страна самая большая по площади?', 1),
('Какой океан самый большой?', 1),
('В какой стране находится Эйфелева башня?', 1),
('Какая столица Франции?', 1),
('Какое животное изображено на гербе Австралии?', 1),
('Какой язык является официальным в Бразилии?', 1),
('Какая страна известна своими пирамидами?', 1),
('Какая столица России?', 1),
('В какой стране находится статуя Свободы?', 1),
('Какой материк пересекает экватор и тропики?', 1),
('Какая страна славится тюльпанами?', 1),
('Как называется денежная единица Японии?', 1),
('В какой стране находится Ниагарский водопад?', 1),
('Какая страна знаменита своими шоколадом и часами?', 1),
('Какой город является столицей Индии?', 1),
('Какой цвет отсутствует на флаге России?', 1),
('В какой стране родился футбол?', 1),
('Какой пустыней знаменита Австралия?', 1),
('Какая страна имеет форму сапога на карте?', 1),
('Какой символ есть на флаге Канады?', 1),
('Какой стране принадлежит остров Мадагаскар?', 1),
('Какой континент самый маленький?', 1),
('Какая страна знаменита своими гондлами?', 1),
('Какой океан омывает побережье России на востоке?', 1),
('В какой стране расположен мост Золотые Ворота?', 1),
('Какая страна производит больше всего бананов?', 1),
('Какой город является столицей Канады?', 1),
('Какая страна славится хоккейным болванчиком?', 1),
('Какая страна дала миру пиццу?', 1),
('Какая река самая длинная в Европе?', 1),
('Какая страна производит больше всего автомобилей?', 1),
('Какая страна имеет флаг с белым полумесяцем и звездой?', 1),
('Какая страна знаменита своими Альпами?', 1),
('В какой стране расположен собор Саграда-Фамилия?', 1),
('Какой город — столица Великобритании?', 1),
('Какая страна знаменита фестивалями маскарадных масок?', 1),
('Какая страна первой запустила человека в космос?', 1),
('Какой город был местом проведения первых Олимпийских игр современности?', 1),
('Какой стране принадлежит остров Хонсю?', 1),
('Какой город — столица Германии?', 1),
('Какая страна производит больше всего шоколада?', 1),
('Какой континент самый жаркий?', 1),
('Какой город — столица Индонезии?', 1),
('Какой язык говорит большинство жителей Канады?', 1),
('Какая страна знаменита карнавалами и самбой?', 1),
('Какой город — столица США?', 1),
('Какой континент самый населённый?', 1),
('Какой город — столица Японии?', 1);

-- Ответы для лёгких вопросов
INSERT INTO answers (question_id, text, is_correct) VALUES
-- 1
(1, 'Канада', FALSE), 
(1, 'США', FALSE), 
(1, 'Россия', TRUE), 
(1, 'Китай', FALSE),
-- 2
(2, 'Атлантический', FALSE), 
(2, 'Тихий', TRUE), 
(2, 'Индийский', FALSE), 
(2, 'Северный Ледовитый', FALSE),
-- 3
(3, 'Италия', FALSE), 
(3, 'Франция', TRUE), 
(3, 'Испания', FALSE), 
(3, 'Португалия', FALSE),
-- 4
(4, 'Рим', FALSE), 
(4, 'Мадрид', FALSE), 
(4, 'Париж', TRUE), 
(4, 'Берлин', FALSE),
-- 5
(5, 'Кенгуру', TRUE), 
(5, 'Коала', FALSE), 
(5, 'Ехидна', FALSE), 
(5, 'Утконос', FALSE),
-- 6
(6, 'Испанский', FALSE), 
(6, 'Английский', FALSE), 
(6, 'Португальский', TRUE), 
(6, 'Французский', FALSE),
-- 7
(7, 'Греция', FALSE), 
(7, 'Египет', TRUE), 
(7, 'Турция', FALSE), 
(7, 'Ирак', FALSE),
-- 8
(8, 'Санкт-Петербург', FALSE), 
(8, 'Новосибирск', FALSE), 
(8, 'Москва', TRUE), 
(8, 'Екатеринбург', FALSE),
-- 9
(9, 'Франция', FALSE), 
(9, 'США', TRUE), 
(9, 'Великобритания', FALSE), 
(9, 'Канада', FALSE),
-- 10
(10, 'Европа', FALSE), 
(10, 'Африка', TRUE), 
(10, 'Азия', FALSE), 
(10, 'Южная Америка', FALSE),
-- 11
(11, 'Дания', FALSE), 
(11, 'Нидерланды', TRUE), 
(11, 'Швеция', FALSE), 
(11, 'Финляндия', FALSE),
-- 12
(12, 'Евро', FALSE), 
(12, 'Йена', TRUE), 
(12, 'Доллар', FALSE), 
(12, 'Фунт', FALSE),
-- 13
(13, 'США и Канада', TRUE), 
(13, 'Бразилия', FALSE), 
(13, 'Аргентина', FALSE), 
(13, 'Австралия', FALSE),
-- 14
(14, 'Франция', FALSE), 
(14, 'Швейцария', TRUE), 
(14, 'Италия', FALSE), 
(14, 'Германия', FALSE),
-- 15
(15, 'Мумбаи', FALSE), 
(15, 'Калькутта', FALSE), 
(15, 'Нью-Дели', TRUE), 
(15, 'Ченнай', FALSE),
-- 16
(16, 'Белый', FALSE), 
(16, 'Синий', FALSE), 
(16, 'Красный', FALSE), 
(16, 'Жёлтый', TRUE),
-- 17
(17, 'Бразилия', FALSE), 
(17, 'США', FALSE), 
(17, 'Великобритания', TRUE), 
(17, 'Германия', FALSE),
-- 18
(18, 'Сахара', FALSE), 
(18, 'Гоби', FALSE), 
(18, 'Большой Виктории', TRUE), 
(18, 'Атакама', FALSE),
-- 19
(19, 'Греция', FALSE), 
(19, 'Италия', TRUE), 
(19, 'Испания', FALSE), 
(19, 'Турция', FALSE),
-- 20
(20, 'Звезда', FALSE), 
(20, 'Лист клёна', TRUE), 
(20, 'Кленовое дерево', FALSE), 
(20, 'Орёл', FALSE),
-- 21
(21, 'Мадагаскар самостоятельный', TRUE), 
(21, 'Франция', FALSE), 
(21, 'ЮАР', FALSE), 
(21, 'Мозамбик', FALSE),
-- 22
(22, 'Европа', FALSE), 
(22, 'Австралия', TRUE), 
(22, 'Антарктида', FALSE), 
(22, 'Азия', FALSE),
-- 23
(23, 'Франция', FALSE), 
(23, 'Италия', TRUE), 
(23, 'Испания', FALSE), 
(23, 'Греция', FALSE),
-- 24
(24, 'Атлантический', FALSE), 
(24, 'Тихий', TRUE), 
(24, 'Индийский', FALSE), 
(24, 'Северный Ледовитый', FALSE),
-- 25
(25, 'Канада', FALSE), 
(25, 'США', TRUE), 
(25, 'Великобритания', FALSE), 
(25, 'Австралия', FALSE),
-- 26
(26, 'Бразилия', FALSE), 
(26, 'Индия', TRUE), 
(26, 'ЮАР', FALSE), 
(26, 'Эквадор', FALSE),
-- 27
(27, 'Торонто', FALSE), 
(27, 'Оттава', TRUE), 
(27, 'Ванкувер', FALSE), 
(27, 'Монреаль', FALSE),
-- 28
(28, 'Россия', FALSE), 
(28, 'Канада', TRUE), 
(28, 'США', FALSE), 
(28, 'Швеция', FALSE),
-- 29
(29, 'Франция', FALSE), 
(29, 'Италия', TRUE), 
(29, 'Испания', FALSE), 
(29, 'Греция', FALSE),
-- 30
(30, 'Волга', TRUE), 
(30, 'Дунай', FALSE), 
(30, 'Темза', FALSE), 
(30, 'Днепр', FALSE),
-- 31
(31, 'США', FALSE), 
(31, 'Германия', FALSE), 
(31, 'Япония', TRUE), 
(31, 'Южная Корея', FALSE),
-- 32
(32, 'Россия', FALSE), 
(32, 'Турция', TRUE), 
(32, 'Китай', FALSE), 
(32, 'Франция', FALSE),
-- 33
(33, 'Франция', FALSE), 
(33, 'Швейцария', TRUE), 
(33, 'Италия', FALSE), 
(33, 'Германия', FALSE),
-- 34
(34, 'Италия', FALSE), 
(34, 'Испания', TRUE), 
(34, 'Португалия', FALSE), 
(34, 'Франция', FALSE),
-- 35
(35, 'Ливерпуль', FALSE), 
(35, 'Манчестер', FALSE), 
(35, 'Лондон', TRUE), 
(35, 'Бирмингем', FALSE),
-- 36
(36, 'Италия', TRUE), 
(36, 'Франция', FALSE), 
(36, 'Испания', FALSE), 
(36, 'Греция', FALSE),
-- 37
(37, 'США', FALSE), 
(37, 'Советский Союз', TRUE), 
(37, 'Великобритания', FALSE), 
(37, 'Франция', FALSE),
-- 38
(38, 'Париж', FALSE), 
(38, 'Афины', TRUE), 
(38, 'Рим', FALSE), 
(38, 'Лондон', FALSE),
-- 39
(39, 'Китай', FALSE), 
(39, 'Япония', TRUE), 
(39, 'Южная Корея', FALSE), 
(39, 'Филиппины', FALSE),
-- 40
(40, 'Берлин', TRUE), 
(40, 'Мюнхен', FALSE), 
(40, 'Гамбург', FALSE), 
(40, 'Франкфурт', FALSE),
-- 41
(41, 'Бельгия', FALSE), 
(41, 'Швейцария', TRUE), 
(41, 'Германия', FALSE), 
(41, 'Италия', FALSE),
-- 42
(42, 'Европа', FALSE), 
(42, 'Африка', TRUE), 
(42, 'Азия', FALSE), 
(42, 'Южная Америка', FALSE),
-- 43
(43, 'Джакарта', TRUE), 
(43, 'Куала-Лумпур', FALSE), 
(43, 'Манила', FALSE), 
(43, 'Бангкок', FALSE),
-- 44
(44, 'Английский', TRUE), 
(44, 'Французский', FALSE), 
(44, 'Испанский', FALSE), 
(44, 'Португальский', FALSE),
-- 45
(45, 'Аргентина', FALSE), 
(45, 'Бразилия', TRUE), 
(45, 'Колумбия', FALSE), 
(45, 'Перу', FALSE),
-- 46
(46, 'Нью-Йорк', FALSE), 
(46, 'Вашингтон', TRUE), 
(46, 'Лос-Анджелес', FALSE), 
(46, 'Чикаго', FALSE),
-- 47
(47, 'Европа', FALSE), 
(47, 'Африка', FALSE), 
(47, 'Азия', TRUE), 
(47, 'Южная Америка', FALSE),
-- 48
(48, 'Осака', FALSE), 
(48, 'Киото', FALSE), 
(48, 'Токио', TRUE), 
(48, 'Хиросима', FALSE);

-- Вопросы среднего уровня (difficulty = 2)
INSERT INTO questions (text, difficulty) VALUES
('Какая страна имеет наибольшее количество соседей?', 2),
('Какой водопад считается самым высоким в мире?', 2),
('Какой остров является крупнейшим в мире?', 2),
('Какая река самая длинная в мире?', 2),
('Какая страна производит больше всего кофе в мире?', 2),
('Какая страна не имеет выхода к морю в Европе?', 2),
('Какой пустынный регион расположен в Африке?', 2),
('Какая страна первой запустила искусственный спутник Земли?', 2),
('Какой город называют "городом вечной весны"?', 2),
('Какой цвет отсутствует на флаге России?', 2),
('Какое государство омывается и Средиземным, и Чёрным морями?', 2),
('Какой язык самый распространённый в мире по числу носителей?', 2),
('В какой стране находится город Петра — древняя столица набатеев?', 2),
('Какой город является столицей Новой Зеландии?', 2),
('Какая страна производит больше всего риса в мире?', 2),
('Какой материк пересекает экватор, северный и южный тропики?', 2),
('Какая страна известна своими традиционными масками и боями быков?', 2),
('Какой город является самым населённым в Африке?', 2),
('Какой стране принадлежит архипелаг Мальдивы?', 2),
('Какая страна граничит с большинством стран Южной Америки?', 2),
('Какой город знаменит своей Пизанской башней?', 2),
('Какая страна использует евро, но не входит в состав ЕС?', 2),
('Какой пролив разделяет Африку и Европу?', 2),
('Какой город является столицей Исландии?', 2),
('Какой стране принадлежит полуостров Юкатан?', 2),
('Какой государственный строй у Саудовской Аравии?', 2),
('Какой город был основан как колония Нидерландов и стал частью США?', 2),
('Какая страна производит больше всего меди в мире?', 2),
('Какой город является столицей Кубы?', 2),
('Какая страна омывается четырьмя морями?', 2),
('Какой континент полностью находится в южном полушарии?', 2),
('Какой стране принадлежит город Александрия?', 2),
('Какой город является столицей Кении?', 2),
('Какая страна производит больше всего бананов в мире?', 2),
('Какой город знаменит карнавалом и статуей Христа-Искупителя?', 2),
('Какой стране принадлежит озеро Верхнее?', 2),
('Какой город является столицей Таиланда?', 2),
('Какая страна не входит в состав НАТО?', 2),
('Какой стране принадлежит гора Аконкагуа?', 2),
('Какой город является столицей Швеции?', 2),
('Какой стране принадлежит остров Бали?', 2),
('Какой стране принадлежит провинция Квебек?', 2),
('Какой стране принадлежит город Буэнос-Айрес?', 2),
('Какой город является столицей Пакистана?', 2),
('Какая страна имеет флаг с изображением луны и звезды?', 2),
('Какой стране принадлежит остров Маврикий?', 2),
('Какой стране принадлежит гора Килиманджаро?', 2),
('Какой город является столицей Омана?', 2),
('Какая страна производит больше всего нефти в мире?', 2),
('Какой город является столицей Алжира?', 2);

-- Добавление правильных и неправильных ответов

-- 1. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(49, 'США', FALSE),
(49, 'Россия', TRUE),
(49, 'Индия', FALSE),
(49, 'Китай', FALSE);

-- 2.
INSERT INTO answers (question_id, text, is_correct) VALUES
(50, 'Ниагарский', FALSE),
(50, 'Виктория', FALSE),
(50, 'Анхель', TRUE),
(50, 'Игуасу', FALSE);

-- 3. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(51, 'Мадагаскар', FALSE),
(51, 'Новая Гвинея', FALSE),
(51, 'Гренландия', TRUE),
(51, 'Куба', FALSE);

-- 4. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(52, 'Нил', FALSE),
(52, 'Янцзы', FALSE),
(52, 'Амазонка', TRUE),
(52, 'Конго', FALSE);

-- 5. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(53, 'Колумбия', FALSE),
(53, 'Бразилия', TRUE),
(53, 'Эфиопия', FALSE),
(53, 'Вьетнам', FALSE);

-- 6. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(54, 'Италия', FALSE),
(54, 'Швейцария', TRUE),
(54, 'Норвегия', FALSE),
(54, 'Португалия', FALSE);

-- 7. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(55, 'Гоби', FALSE),
(55, 'Сахара', TRUE),
(55, 'Калахари', FALSE),
(55, 'Атакама', FALSE);

-- 8. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(56, 'США', FALSE),
(56, 'Советский Союз', TRUE),
(56, 'Великобритания', FALSE),
(56, 'Франция', FALSE);

-- 9. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(57, 'Сантьяго', FALSE),
(57, 'Мехико', FALSE),
(57, 'Кито', TRUE),
(57, 'Манила', FALSE);

-- 10. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(58, 'Белый', FALSE),
(58, 'Синий', FALSE),
(58, 'Красный', FALSE),
(58, 'Жёлтый', TRUE);

-- 11. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(59, 'Турция', TRUE),
(59, 'Грузия', FALSE),
(59, 'Россия', FALSE),
(59, 'Украина', FALSE);

-- 12. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(60, 'Английский', FALSE),
(60, 'Хинди', FALSE),
(60, 'Китайский (мандарин)', TRUE),
(60, 'Испанский', FALSE);

-- 13. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(61, 'Саудовская Аравия', FALSE),
(61, 'ОАЭ', FALSE),
(61, 'Иордания', TRUE),
(61, 'Ливан', FALSE);

-- 14. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(62, 'Окленд', FALSE),
(62, 'Веллингтон', TRUE),
(62, 'Крайстчерч', FALSE),
(62, 'Данидин', FALSE);

-- 15. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(63, 'Индия', FALSE),
(63, 'Китай', TRUE),
(63, 'Таиланд', FALSE),
(63, 'Вьетнам', FALSE);

-- 16. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(64, 'Африка', TRUE),
(64, 'Южная Америка', FALSE),
(64, 'Азия', FALSE),
(64, 'Австралия', FALSE);

-- 17. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(65, 'Испания', TRUE),
(65, 'Португалия', FALSE),
(65, 'Италия', FALSE),
(65, 'Франция', FALSE);

-- 18. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(66, 'Претория', FALSE),
(66, 'Каир', FALSE),
(66, 'Лагос', TRUE),
(66, 'Дакар', FALSE);

-- 19. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(67, 'Индия', FALSE),
(67, 'Мальдивы — независимое государство', TRUE),
(67, 'Шри-Ланка', FALSE),
(67, 'Пакистан', FALSE);

-- 20.
INSERT INTO answers (question_id, text, is_correct) VALUES
(68, 'Бразилия', TRUE),
(68, 'Перу', FALSE),
(68, 'Колумбия', FALSE),
(68, 'Аргентина', FALSE);

-- 21. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(69, 'Рим', FALSE),
(69, 'Милан', FALSE),
(69, 'Пиза', TRUE),
(69, 'Неаполь', FALSE);

-- 22. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(70, 'Германия', FALSE),
(70, 'Сан-Марино', TRUE),
(70, 'Польша', FALSE),
(70, 'Франция', FALSE);

-- 23. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(71, 'Босфор', FALSE),
(71, 'Гибралтарский', TRUE),
(71, 'Ла-Манш', FALSE),
(71, 'Магелланов', FALSE);

-- 24. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(72, 'Рейкьявик', TRUE),
(72, 'Осло', FALSE),
(72, 'Копенгаген', FALSE),
(72, 'Хельсинки', FALSE);

-- 25. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(73, 'США', FALSE),
(73, 'Мексика', TRUE),
(73, 'Куба', FALSE),
(73, 'Бразилия', FALSE);

-- 26. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(74, 'Республика', FALSE),
(74, 'Федерация', FALSE),
(74, 'Монархия', TRUE),
(74, 'Демократия', FALSE);

-- 27. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(75, 'Вашингтон', FALSE),
(75, 'Нью-Йорк', TRUE),
(75, 'Бостон', FALSE),
(75, 'Филадельфия', FALSE);

-- 28. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(76, 'США', FALSE),
(76, 'Перу', FALSE),
(76, 'Чили', TRUE),
(76, 'Канада', FALSE);

-- 29. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(77, 'Варадеро', FALSE),
(77, 'Сантьяго', FALSE),
(77, 'Гавана', TRUE),
(77, 'Тринидад', FALSE);

-- 30. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(78, 'Россия', FALSE),
(78, 'Турция', TRUE),
(78, 'Италия', FALSE),
(78, 'Украина', FALSE);

-- 31. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(79, 'Австралия', TRUE),
(79, 'Африка', FALSE),
(79, 'Европа', FALSE),
(79, 'Южная Америка', FALSE);

-- 32. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(80, 'Судан', FALSE),
(80, 'Египет', TRUE),
(80, 'Ливия', FALSE),
(80, 'Сирия', FALSE);

-- 33.
INSERT INTO answers (question_id, text, is_correct) VALUES
(81, 'Додома', FALSE),
(81, 'Найроби', TRUE),
(81, 'Момбаса', FALSE),
(81, 'Лусака', FALSE);

-- 34. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(82, 'Индия', TRUE),
(82, 'ЮАР', FALSE),
(82, 'Эквадор', FALSE),
(82, 'Бразилия', FALSE);

-- 35. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(83, 'Сан-Паулу', FALSE),
(83, 'Рио-де-Жанейро', TRUE),
(83, 'Салвадор', FALSE),
(83, 'Белу-Оризонти', FALSE);

-- 36. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(84, 'Канада', FALSE),
(84, 'США', FALSE),
(84, 'США и Канада', TRUE),
(84, 'Мексика', FALSE);

-- 37. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(85, 'Чиангмай', FALSE),
(85, 'Паттайя', FALSE),
(85, 'Бангкок', TRUE),
(85, 'Аюттхая', FALSE);

-- 38. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(86, 'Германия', FALSE),
(86, 'Испания', FALSE),
(86, 'Австрия', TRUE),
(86, 'Франция', FALSE);

-- 39. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(87, 'Перу', FALSE),
(87, 'Чили', FALSE),
(87, 'Аргентина', TRUE),
(87, 'Боливия', FALSE);

-- 40. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(88, 'Осло', FALSE),
(88, 'Копенгаген', FALSE),
(88, 'Стокгольм', TRUE),
(88, 'Хельсинки', FALSE);

-- 41. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(89, 'Малайзия', FALSE),
(89, 'Индонезия', TRUE),
(89, 'Филиппины', FALSE),
(89, 'Таиланд', FALSE);

-- 42. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(90, 'США', FALSE),
(90, 'Канада', TRUE),
(90, 'Франция', FALSE),
(90, 'Бразилия', FALSE);

-- 43.
INSERT INTO answers (question_id, text, is_correct) VALUES
(91, 'Бразилия', FALSE),
(91, 'Аргентина', TRUE),
(91, 'Уругвай', FALSE),
(91, 'Парагвай', FALSE);

-- 44. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(92, 'Каракас', FALSE),
(92, 'Исламабад', TRUE),
(92, 'Лахор', FALSE),
(92, 'Пешавар', FALSE);

-- 45. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(93, 'Россия', FALSE),
(93, 'Турция', TRUE),
(93, 'Германия', FALSE),
(93, 'Франция', FALSE);

-- 46. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(94, 'Маврикий — независимое государство', TRUE),
(94, 'Франция', FALSE),
(94, 'Великобритания', FALSE),
(94, 'ЮАР', FALSE);

-- 47. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(95, 'Кения', FALSE),
(95, 'Танзания', TRUE),
(95, 'Уганда', FALSE),
(95, 'Замбия', FALSE);

-- 48. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(96, 'Салала', FALSE),
(96, 'Абу-Даби', FALSE),
(96, 'Маскат', TRUE),
(96, 'Эр-Рияд', FALSE);

-- 49. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(97, 'США', TRUE),
(97, 'Саудовская Аравия', FALSE),
(97, 'Россия', FALSE),
(97, 'Иран', FALSE);

-- 50.
INSERT INTO answers (question_id, text, is_correct) VALUES
(98, 'Тунис', FALSE),
(98, 'Блида', FALSE),
(98, 'Алжир', TRUE),
(98, 'Триполи', FALSE);


-- Вопросы сложного уровня (difficulty = 3)
INSERT INTO questions (text, difficulty) VALUES
('Какая страна была первой, где начали использовать бумагу?', 3),
('Какой город был центром древнейшей цивилизации Месопотамии?', 3),
('Какой континент полностью покрыт ледяным щитом?', 3),
('Какой альпинист первым достиг вершины Эвереста?', 3),
('Какой химический элемент назван в честь страны?', 3),
('Какой корабль затонул в 1912 году после столкновения с айсбергом?', 3),
('Какая страна первой провела всеобщие выборы с участием женщин?', 3),
('Какой исторический договор положил конец Первой мировой войне?', 3),
('Какое государство известно как "страна тысячи озёр"?', 3),
('Какой остров является самым населённым в мире?', 3),
('Какая страна производит больше всего электроэнергии на душу населения?', 3),
('Какой город расположен на двух континентах?', 3),
('Какая страна до 2011 года использовала флаг с единственным цветом — зелёным?', 3),
('Какой язык является самым распространённым в интернете после английского?', 3),
('Какая страна первой признала независимость США?', 3),
('Какой город называют "воротами в Арктику"?', 3),
('Какой государственный строй у Бутана?', 3),
('Какая страна имеет самую большую протяжённость береговой линии в мире?', 3),
('Какая страна не подписала Конвенцию ООН по борьбе с изменением климата?', 3),
('Какой город является столицей самого молодого государства мира?', 3),
('Какой стране принадлежит территория Голанских высот?', 3),
('Какой город находится на самом высоком уровне над уровнем моря?', 3),
('Какая страна имеет наибольшее количество официальных языков?', 3),
('Какой город знаменит своими подземными галереями и музеями?', 3),
('Какой стране принадлежит самый большой искусственный остров в мире?', 3),
('Какой город стал первой европейской культурной столицей?', 3),
('Какой стране принадлежит флаг с изображением птицы?', 3),
('Какой город является родиной шахмат?', 3),
('Какой стране принадлежит атолл Бикини?', 3),
('Какой город является самым удалённым от экватора?', 3),
('Какой стране принадлежит самый большой пресный водоём в Европе?', 3),
('Какой город был разрушен вулканом Помпеи?', 3),
('Какая страна имеет формулу счастья вместо ВВП?', 3),
('Какой стране принадлежит гора Косцюшко?', 3),
('Какой город является столицей самого маленького государства в мире?', 3),
('Какой стране принадлежит самый старый университет в Европе?', 3),
('Какой стране принадлежит остров Рождества?', 3),
('Какой город является самым западным в мире?', 3),
('Какой стране принадлежит самый длинный железнодорожный маршрут без пересадок?', 3),
('Какой стране принадлежит полуостров Камчатка?', 3),
('Какой стране принадлежит остров Пасхи?', 3),
('Какой стране принадлежит самый высокий водопад внутри страны?', 3),
('Какой стране принадлежит самый большой пресноводный остров в мире?', 3),
('Какой стране принадлежит самая большая система пещер в мире?', 3),
('Какой стране принадлежит самое большое количество памятников ЮНЕСКО?', 3);

-- 1. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(99, 'Древний Египет', FALSE),
(99, 'Китай', TRUE),
(99, 'Греция', FALSE),
(99, 'Римская империя', FALSE);

-- 2. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(100, 'Ур', TRUE),
(100, 'Александрия', FALSE),
(100, 'Карфаген', FALSE),
(100, 'Персеполь', FALSE);

-- 3. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(101, 'Арктика', FALSE),
(101, 'Антарктида', TRUE),
(101, 'Гренландия', FALSE),
(101, 'Азия', FALSE);

-- 4. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(102, 'Эдмунд Хиллари', TRUE),
(102, 'Жан-Клод Ван Дамм', FALSE),
(102, 'Райнхольд Месснер', FALSE),
(102, 'Сергей Шаповалов', FALSE);

-- 5. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(103, 'Уран', FALSE),
(103, 'Полоний', TRUE),
(103, 'Калий', FALSE),
(103, 'Магний', FALSE);

-- 6.
INSERT INTO answers (question_id, text, is_correct) VALUES
(104, 'Олимпик', FALSE),
(104, 'Титаник', TRUE),
(104, 'Бисмарк', FALSE),
(104, 'Черная жемчужина', FALSE);

-- 7. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(105, 'США', FALSE),
(105, 'Новая Зеландия', TRUE),
(105, 'Франция', FALSE),
(105, 'Великобритания', FALSE);

-- 8. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(106, 'Версальский договор', TRUE),
(106, 'Тильзитский мир', FALSE),
(106, 'Парижский мир', FALSE),
(106, 'Кучук-Кайнарджийский договор', FALSE);

-- 9. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(107, 'Канада', FALSE),
(107, 'Финляндия', TRUE),
(107, 'Россия', FALSE),
(107, 'Швеция', FALSE);

-- 10. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(108, 'Мадагаскар', FALSE),
(108, 'Ява', TRUE),
(108, 'Суматра', FALSE),
(108, 'Куба', FALSE);

-- 11. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(109, 'Исландия', FALSE),
(109, 'Норвегия', TRUE),
(109, 'Франция', FALSE),
(109, 'США', FALSE);

-- 12. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(110, 'Москва', FALSE),
(110, 'Стамбул', TRUE),
(110, 'Каир', FALSE),
(110, 'Дакар', FALSE);

-- 13. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(111, 'Ливия', TRUE),
(111, 'ОАЭ', FALSE),
(111, 'Йемен', FALSE),
(111, 'Алжир', FALSE);

-- 14. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(112, 'Русский', FALSE),
(112, 'Китайский (мандарин)', TRUE),
(112, 'Испанский', FALSE),
(112, 'Французский', FALSE);

-- 15. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(113, 'Франция', TRUE),
(113, 'Испания', FALSE),
(113, 'Голландия', FALSE),
(113, 'Португалия', FALSE);

-- 16.
INSERT INTO answers (question_id, text, is_correct) VALUES
(114, 'Норильск', FALSE),
(114, 'Салехард', FALSE),
(114, 'Тромсё', FALSE),
(114, 'Мурманск', TRUE);

-- 17. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(115, 'Республика', FALSE),
(115, 'Федерация', FALSE),
(115, 'Конституционная монархия', TRUE),
(115, 'Теократия', FALSE);

-- 18. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(116, 'Индонезия', FALSE),
(116, 'Канада', TRUE),
(116, 'США', FALSE),
(116, 'Австралия', FALSE);

-- 19.
INSERT INTO answers (question_id, text, is_correct) VALUES
(117, 'США', FALSE),
(117, 'Туркменистан', TRUE),
(117, 'Россия', FALSE),
(117, 'Китай', FALSE);

-- 20. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(118, 'Джуба', TRUE),
(118, 'Приштина', FALSE),
(118, 'Сухуми', FALSE),
(118, 'Цхинвали', FALSE);

-- 21.
INSERT INTO answers (question_id, text, is_correct) VALUES
(119, 'Сирия', FALSE),
(119, 'Израиль (де-факто)', TRUE),
(119, 'Ливан', FALSE),
(119, 'Иордания', FALSE);

-- 22. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(120, 'Ла-Пас (Боливия)', TRUE),
(120, 'Кито (Эквадор)', FALSE),
(120, 'Лхаса (Тибет)', FALSE),
(120, 'Кабул (Афганистан)', FALSE);

-- 23. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(121, 'Индия', FALSE),
(121, 'ЮАР', TRUE),
(121, 'Швейцария', FALSE),
(121, 'Бельгия', FALSE);

-- 24. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(122, 'Киев', FALSE),
(122, 'Одесса', TRUE),
(122, 'Санкт-Петербург', FALSE),
(122, 'Прага', FALSE);

-- 25. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(123, 'Китай', FALSE),
(123, 'ОАЭ', TRUE),
(123, 'Япония', FALSE),
(123, 'США', FALSE);

-- 26. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(124, 'Афины', TRUE),
(124, 'Париж', FALSE),
(124, 'Рим', FALSE),
(124, 'Лондон', FALSE);

-- 27. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(125, 'Новая Зеландия', TRUE),
(125, 'Филиппины', FALSE),
(125, 'Чили', FALSE),
(125, 'Украина', FALSE);

-- 28.
INSERT INTO answers (question_id, text, is_correct) VALUES
(126, 'Персия', FALSE),
(126, 'Индия', TRUE),
(126, 'Китай', FALSE),
(126, 'Египет', FALSE);

-- 29. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(127, 'США', FALSE),
(127, 'Маршалловы Острова', TRUE),
(127, 'Япония', FALSE),
(127, 'Австралия', FALSE);

-- 30.
INSERT INTO answers (question_id, text, is_correct) VALUES
(128, 'Осло', FALSE),
(128, 'Санкт-Петербург', FALSE),
(128, 'Рейкьявик', FALSE),
(128, 'Норильск', TRUE);

-- 31.
INSERT INTO answers (question_id, text, is_correct) VALUES
(129, 'Финляндия', FALSE),
(129, 'Россия', TRUE),
(129, 'Швеция', FALSE),
(129, 'Украина', FALSE);

-- 32. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(130, 'Помпеи', TRUE),
(130, 'Геркуланеум', FALSE),
(130, 'Неаполь', FALSE),
(130, 'Рим', FALSE);

-- 33.
INSERT INTO answers (question_id, text, is_correct) VALUES
(131, 'Бутан', TRUE),
(131, 'Непал', FALSE),
(131, 'Таиланд', FALSE),
(131, 'Япония', FALSE);

-- 34. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(132, 'Польша', FALSE),
(132, 'Австралия', TRUE),
(132, 'США', FALSE),
(132, 'Новая Зеландия', FALSE);

-- 35.
INSERT INTO answers (question_id, text, is_correct) VALUES
(133, 'Ватикан', TRUE),
(133, 'Сан-Марино', FALSE),
(133, 'Науру', FALSE),
(133, 'Монако', FALSE);

-- 36. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(134, 'Италия', TRUE),
(134, 'Франция', FALSE),
(134, 'Португалия', FALSE),
(134, 'Испания', FALSE);

-- 37. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(135, 'США', FALSE),
(135, 'Австралия', TRUE),
(135, 'Китай', FALSE),
(135, 'Индия', FALSE);

-- 38. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(136, 'Самоа', FALSE),
(136, 'Киритимати', TRUE),
(136, 'Гавайи', FALSE),
(136, 'Фиджи', FALSE);

-- 39. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(137, 'Канада', FALSE),
(137, 'Россия', TRUE),
(137, 'США', FALSE),
(137, 'Китай', FALSE);

-- 40.
INSERT INTO answers (question_id, text, is_correct) VALUES
(138, 'США', FALSE),
(138, 'Россия', TRUE),
(138, 'Япония', FALSE),
(138, 'Китай', FALSE);

-- 41.
INSERT INTO answers (question_id, text, is_correct) VALUES
(139, 'Чили', TRUE),
(139, 'Перу', FALSE),
(139, 'Эквадор', FALSE),
(139, 'Бразилия', FALSE);

-- 42. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(140, 'Венесуэла', TRUE),
(140, 'Колумбия', FALSE),
(140, 'Бразилия', FALSE),
(140, 'Перу', FALSE);

-- 43. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(141, 'Канада', TRUE),
(141, 'США', FALSE),
(141, 'Россия', FALSE),
(141, 'Бразилия', FALSE);

-- 44. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(142, 'Украина', FALSE),
(142, 'США', TRUE),
(142, 'Китай', FALSE),
(142, 'Испания', FALSE);

-- 45. 
INSERT INTO answers (question_id, text, is_correct) VALUES
(143, 'Италия', TRUE),
(143, 'Китай', FALSE),
(143, 'Франция', FALSE),
(143, 'Испания', FALSE);

