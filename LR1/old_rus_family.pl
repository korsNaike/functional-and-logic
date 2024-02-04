
man(voeneg).
man(ratibor).
man(boguslav).
man(velerad).
man(duhovlad).
man(svyatoslav).
man(dobrozhir).
man(bogomil).
man(zlatomir).

woman(goluba).
woman(lubomila).
woman(bratislava).
woman(veslava).
woman(zhdana).
woman(bozhedara).
woman(broneslava).
woman(veselina).
woman(zdislava).

parent(voeneg,ratibor).
parent(voeneg,bratislava).
parent(voeneg,velerad).
parent(voeneg,zhdana).

parent(goluba,ratibor).
parent(goluba,bratislava).
parent(goluba,velerad).
parent(goluba,zhdana).

parent(ratibor,svyatoslav).
parent(ratibor,dobrozhir).
parent(lubomila,svyatoslav).
parent(lubomila,dobrozhir).

parent(boguslav,bogomil).
parent(boguslav,bozhedara).
parent(bratislava,bogomil).
parent(bratislava,bozhedara).

parent(velerad,broneslava).
parent(velerad,veselina).
parent(veslava,broneslava).
parent(veslava,veselina).

parent(duhovlad,zdislava).
parent(duhovlad,zlatomir).
parent(zhdana,zdislava).
parent(zhdana,zlatomir).

% men/0
men:- man(X), write(X), nl, fail.

% women/0
women:- woman(X), write(X), nl, fail.

% children(+X)
children(X):-  parent(X, Y), write(Y), nl, fail.

% mother(+X, +Y)
mother(X, Y):- woman(X), parent(X, Y).

% brother(+X, +Y)
brother(X, Y):- man(X), parent(Z, X), parent(Z, Y), X \= Y.

% sister(+X, +Y)
sister(X, Y):- woman(X), parent(Z, X), parent(Z, Y), X \= Y.

% brothers(+X)
brothers(X):- brother(Y, X), write(Y), nl, fail.

% b_s(+X, +Y)
b_s(X, Y):- brother(X, Y).
b_s(X, Y):- sister(X, Y).

% b_s(+X)
b_s(X):- b_s(Y, X), write(Y), nl, fail.

% daughter(+X, +Y)
daughter(X, Y):- woman(X), parent(Y, X).

% wife(+X, +Y)
wife(X, Y):- woman(X), parent(X, Z), parent(Y, Z), X \= Y.

% суфиксом _facts отмечены те предикаты, которые используют только базу фактов.

% grand_so(+X, +Y)
grand_so(X, Y):- man(X), parent(Parent, X), parent(Y, Parent).

% grand_sons(+X)
grand_sons(X):- grand_so(Y, X), write(Y), nl, fail.

% grand_sons_facts(+X)
grand_sons_facts(X):- man(Y), parent(Parent, Y), parent(X, Parent), write(Y), nl, fail.

% grand_ma_and_son(+X, +Y)
grand_ma_and_son(X, Y):- woman(Y), grand_so(X, Y).
grand_ma_and_son(X, Y):- woman(X), grand_so(Y, X).

% grand_ma_and_son_facts(+X, +Y)
grand_ma_and_son_facts(X, Y):- woman(Y), man(X), parent(Parent, X), parent(Y, Parent).
grand_ma_and_son_facts(Y, X):- woman(Y), man(X), parent(Parent, X), parent(Y, Parent).

% aunt(+X, +Y)
aunt(X, Y):- man(X), parent(Parent_plem, Y), brother(X, Parent_plem).

% aunt_facts(+X, +Y)
aunt_facts(X, Y):- man(X), parent(Parent_plem, Y), parent(ObsParent, Parent_plem), parent(ObsParent, X), Parent_plem \= X.

% aunts(+X)
aunts(X):- aunt(Y, X), write(Y), nl, fail.

% aunts_facts(+Y)
aunts_facts(Y):- man(X), parent(Parent_plem, Y), parent(ObsParent, Parent_plem), parent(ObsParent, X), Parent_plem \= X, write(X), nl, fail.