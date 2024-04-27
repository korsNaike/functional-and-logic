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