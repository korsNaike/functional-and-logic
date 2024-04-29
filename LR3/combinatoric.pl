%in_list(?List, ?El) - если El унифицирована, то проверить, входит ли данный элемент в массив
% если El не унифицирован, будут возвращать все элементы по очереди, до точки.
% если List не унифицирован, будет возвращён список с El.
in_list([El|_], El).
in_list([_|T], El):- in_list(T, El).

%razm_povt(+Alphabet, +K, -Razm) - получить в Razm очередное размещение с повторениями
razm_povt(Alphabet, N, Razm):- razm_povt(Alphabet, N, [], Razm).
razm_povt(_, 0, Razm, Razm):-!.
razm_povt(Alphabet, NCur, RazmCur, Razm):- in_list(Alphabet, El), NNew is NCur - 1, razm_povt(Alphabet, NNew, [El|RazmCur], Razm).

% print_all_razm(+Alphabet, +K) - напечатать все размещения с повторениями
print_all_razm(Alphabet, K):-
  razm_povt(Alphabet, K, Razm),
  writeln(Razm),
  false.

% comb(+Alphabet, +K, -Comb) - получить в Comb очередное сочетание
comb(_, 0, []).
comb([X|T], K, [X|Comb]) :-
  K > 0,
  K1 is K - 1,
  comb(T, K1, Comb).
comb([_|T], K, Comb) :-
  K > 0,
  comb(T, K, Comb).

% print_all_comb(+Alphabet, +K) - напечатать все сочетания без повторений
print_all_comb(Alphabet, K):-
  comb(Alphabet, K, Comb),
  writeln(Comb),
  false.

% make_pos_list(+K, +CurPos, -ListPos) - сформировать список для позиций
make_pos_list(K, K, []):-!.
make_pos_list(K, CurPos, [NewPos|TailPos]) :- NewPos is CurPos + 1, make_pos_list(K, NewPos, TailPos).

% make_3a_empty_word(+K, +CurIndex, +PosList, -WordEmpty3a) - построить слово, где на позициях из PosList будет стоять буква a
make_3a_empty_word(K, K, _, []):- !.
make_3a_empty_word(K, CurIndex, [NewIndex|PosTail], [a|Tail]) :- 
	NewIndex is CurIndex + 1, make_3a_empty_word(K, NewIndex, PosTail, Tail), !.
make_3a_empty_word(K, CurIndex, PosList, [_|Tail]) :- 
	NewIndex is CurIndex + 1, make_3a_empty_word(K, NewIndex, PosList, Tail).	

% build_word (-Word, +WordEmpty3a, +RestWord) - забить непустые места в слове WordEmpty3a элементами из RestWord
build_word([],[],_):- !.
build_word([a|WordTail], [X|WordEmpty3aTail], RestWord) :- 
	nonvar(X), build_word(WordTail, WordEmpty3aTail, RestWord),  !.
build_word([Y|WordTail],[X|WordEmpty3aTail],[Y|RestWordTail]) :- 
	var(X), build_word(WordTail, WordEmpty3aTail, RestWordTail).

% build_3a_words_of_k(+Alphabet, +K, -Word)
build_3a_words_of_k(Alphabet, K, Word) :- 
  make_pos_list(K, 0, PosList), % формируем список-алфавит позиций
	comb(PosList, 3, Pos_a_List), % строим сочетание позиций
  make_3a_empty_word(K, 0, Pos_a_List, WordEmpty3a), % забиваем в слово буквы a и оставляем пустые места
  Alphabet = [a|NewAlphabet], % убираем буква a из алфавита
	M is K - 3,
  razm_povt(NewAlphabet, M, [], RestWord), % строим размещение оставшегося алфавита
  build_word(Word, WordEmpty3a, RestWord). % строим слово

% Объект 1

% razm(+Alphabet, +K, -Razm) - записать в Razm очередное размещение без повторений
razm(Alphabet, K, Razm) :-
    length(Alphabet, N),
    razm(Alphabet, K, N, Razm).

razm(_, 0, _, []).
razm(Alphabet, K, N, [X|Xs]) :-
    select(X, Alphabet, RestAlphabet), % выбирает из Alphabet X, RestAlphabet - оставшийся алфавит 
    K1 is K - 1,
    razm(RestAlphabet, K1, N, Xs).

% Объект 4

% comb_with_reps(+Alphabet, +K, -Comb) - построить в Comb очередное сочетание с повторениями
comb_povt(_, 0, []):- !.
comb_povt([Letter|Alphabet], K, [Letter|PrevResult]):- KNew is K - 1, comb_povt([Letter|Alphabet], KNew, PrevResult).
comb_povt([_|Alphabet], K, PrevResult):- comb_povt(Alphabet, K, PrevResult).

% Объект 7

% make_position_word(+LetterForPositions, +K, +CurIndex, +PosList, -PositionWord) - построить слово, где на позициях из PosList будет стоять буква LetterForPositions
make_position_word(_, K, K, _, []):- !.
make_position_word(Letter, K, CurIndex, [NewIndex|PosTail], [Letter|Tail]) :- 
	NewIndex is CurIndex + 1, make_position_word(Letter, K, NewIndex, PosTail, Tail), !.
make_position_word(Letter, K, CurIndex, PosList, [_|Tail]) :- 
	NewIndex is CurIndex + 1, make_position_word(Letter, K, NewIndex, PosList, Tail).

% build_word_letter (-Word, +Letter, +PositionWord, +RestWord) - забить непустые места в слове PositionWord элементами из RestWord
build_word_letter([], _, [], _):- !.
build_word_letter([Letter|WordTail], Letter, [X|WordEmpty3aTail], RestWord) :- 
	nonvar(X), build_word_letter(WordTail, Letter, WordEmpty3aTail, RestWord),  !.
build_word_letter([Y|WordTail], Letter, [X|WordEmpty3aTail], [Y|RestWordTail]) :- 
	var(X), build_word_letter(WordTail, Letter, WordEmpty3aTail, RestWordTail).

% word_length5_letter_repeat2(+Alphabet, -Word) - построить в Word слово длины 5, где 2 буквы повторяются, а остальные нет
word_length5_letter_repeat2(Alphabet, Word):-
  select(RepeatLetter, Alphabet, AlphabetWithoutRepLetter), % выбираем 1 букву, которая будет повторяться
  make_pos_list(5, 0, PosList), % формируем список-алфавит позиций
  comb(PosList, 2, PosRepeatLetter), % строим сочетание позиций
  make_position_word(RepeatLetter, 5, 0, PosRepeatLetter, PositionWord), % формируем слово, где на позициях из сочетания стоит буква, которую выбрали
  M is 5 - 2, % осталось 5 - 2 букв для заполнения
  razm(AlphabetWithoutRepLetter, M, RestWord), % строим размещение (без повторений) оставшегося алфавита
  build_word_letter(Word, RepeatLetter, PositionWord, RestWord). % строим слово

% Объект 8

% replace(+Index, +CurList, +Value, -ResList) - заменить элемент на индексе Index в списке CurList значением Value и вернуть новый список в ResList
replace(1, [_|Xs], Value, [Value|Xs]).
replace(N, [X|Xs], Value, [X|Ys]) :-
    N > 1,
    N1 is N - 1,
    replace(N1, Xs, Value, Ys).

% make_position_word_from_other(+Positions, +LetterToInsert, +WordToInsert, -ResultWord) - места в WordToInsert в Positions заменить на LetterToInsert и вернуть результат в ResultWord
make_position_word_from_other([], _, ResultWord, ResultWord):- !.
make_position_word_from_other([Position|PositionsFirstWord], LetterToInsert, WordToInsert, ResultWord):-
  replace(Position, WordToInsert, LetterToInsert, NewCurWord),
  make_position_word_from_other(PositionsFirstWord, LetterToInsert, NewCurWord, ResultWord), !.

% make_position_word_from_other_list(+Positions, +LettersForInsert, +WordToInsert, -ResultWord) - места в WordToInsert в Positions заменить на элементы из LettersForInsert и вернуть результат в ResultWord
make_position_word_from_other_list([], _, ResultWord, ResultWord):- !.
make_position_word_from_other_list([Position|PositionsFirstWord], [LetterToInsert|OtherLetters], WordToInsert, ResultWord):-
  replace(Position, WordToInsert, LetterToInsert, NewCurWord),
  make_position_word_from_other_list(PositionsFirstWord, OtherLetters, NewCurWord, ResultWord), !.

% make_pos_list_without(+PosList, +ExceptPosList, -ResultPosList) - сформировать новый список из PosList, без элементов списка ExceptPosList
make_pos_list_without(PosList, ExceptPosList, ResultPosList):- make_pos_list_without(PosList, ExceptPosList, [], ResultPosList), !.
make_pos_list_without([], _, ResultPosList, ResultPosList):- !.
make_pos_list_without([Position|TailPosList], ExceptPosList, CurResult, ResultPosList):- % если Position есть в ExceptPosList, то пропускаем его
  member(Position, ExceptPosList),
  make_pos_list_without(TailPosList, ExceptPosList, CurResult, ResultPosList), !.
make_pos_list_without([Position|TailPosList], ExceptPosList, CurResult, ResultPosList):- % если же его нет, записываем в аккумулятор
  make_pos_list_without(TailPosList, ExceptPosList, [Position|CurResult], ResultPosList), !.


% word_length6_2_repeat2(+Alphabet, -Word) - построить слово длины 6, где ровно 2 буквы повторяются 2 раза, остальные не повторяются
word_length6_2_repeat2(Alphabet, Word):-
  select(RepeatLetter, Alphabet, AlphabetWithoutRepLetter), % выбираем первую букву, которая будет повторяться
  make_pos_list(6, 0, PosList), % формируем список-алфавит позиций
  comb(PosList, 2, PosRepeatLetter), % строим сочетание позиций
  make_position_word(RepeatLetter, 6, 0, PosRepeatLetter, PositionWord), % формируем слово, где на позициях из сочетания стоит буква, которую выбрали
  M is 6 - 2, % осталось 6 - 2 букв для заполнения

  select(RepeatLetter2, AlphabetWithoutRepLetter, NewAlphabet), % выбираем вторую букву, которая будет повторяться
  make_pos_list_without(PosList, PosRepeatLetter, SecondPosList), % создаём алфавит позиций для второй буквы
  comb(SecondPosList, 2, SecondPosRepeatLetter), % составляем второе сочетание позиций
  make_position_word_from_other(SecondPosRepeatLetter, RepeatLetter2, PositionWord, SecondPositionWord), % заполняем позиции в слове другой буквой
  CountRest is M - 2, % осталось мест для заполнения

  razm(NewAlphabet, CountRest, RestWord), % строим размещение (без повторений) оставшегося алфавита
  make_pos_list_without(SecondPosList, SecondPosRepeatLetter, PosListForRazm), % делаем список позиций, куда будут установлены буквы из размещения
  make_position_word_from_other_list(PosListForRazm, RestWord, SecondPositionWord, Word). % заполняем оставшиеся позиции.

% Предикаты для вывода этих комбинаторных объектов в файл

% write_list_str(+String) - напечатать список как строку
write_list_str([]):-!.
write_list_str([H|List]):- write(H), write_list_str(List).

% all_razm_no_povt(+Alphabet, +K) - Печать всех размещений без повторений
all_razm_no_povt(Alphabet, K):-
  razm(Alphabet, K, Razm),
  write_list_str(Razm), nl,
  fail.

% all_razm_no_povt_file(+FilePath, +Alphabet, +K) - Печать всех размещений без повторений в файл
all_razm_no_povt_file(FilePath, Alphabet, K):-
  tell(FilePath),
  not(all_razm_no_povt(Alphabet, K)), % отрицание нужно, чтобы дойти до told
  told, !.

% all_comb_povt(+Alphabet, +K) - печать всех сочетаний с повторениями
all_comb_povt(Alphabet, K):-
  comb_povt(Alphabet, K, Comb),
  write_list_str(Comb), nl,
  fail.

% all_comb_povt_file(+FilePath, +Alphabet, +K) - печать всех сочетаний с повторениями в файл
all_comb_povt_file(FilePath, Alphabet, K):-
  tell(FilePath),
  not(all_comb_povt(Alphabet, K)),
  told, !.

% all_word5_repeat2(+Alphabet) - печать всех слов длины 5, где только 1 буква повторяется 2 раза
all_word5_repeat2(Alphabet):-
  word_length5_letter_repeat2(Alphabet, Word),
  write_list_str(Word), nl,
  fail.

% all_word5_repeat2_file(+FilePath, +Alphabet) - печать всех слов длины 5, где только 1 буква повторяется 2 раза, в файл
all_word5_repeat2_file(FilePath, Alphabet):-
  tell(FilePath),
  not(all_word5_repeat2(Alphabet)),
  told, !.

% all_word6_2_repeat2(+Alphabet) - печать всех слов длины 6, где ровно 2 буквы повторяется 2 раза
all_word6_2_repeat2(Alphabet):-
  word_length6_2_repeat2(Alphabet, Word),
  write_list_str(Word), nl,
  fail.

% all_word6_2_repeat2(+FilePath, +Alphabet) - печать всех слов длины 6, где ровно 2 буквы повторяется 2 раза, в файл
all_word6_2_repeat2_file(FilePath, Alphabet):-
  tell(FilePath),
  not(all_word6_2_repeat2(Alphabet)),
  told, !.