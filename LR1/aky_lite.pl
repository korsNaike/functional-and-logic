high(ruby,1).
high(c_sharp,1).
high(python,1).
high(c_plu_plus,1).
high(f_sharp,1).
high(prolog,1).
high(c,1).
high(asm,0).
high(swift, 1).
high(kotlin, 1).
high(dart, 1).
high(go, 1).
high(javascript, 1).

decl(ruby,0).
decl(c_sharp,0).
decl(python,0).
decl(c_plu_plus,0).
decl(f_sharp,1).
decl(prolog,1).
decl(c,0).
decl(asm,0).
decl(swift, 0).
decl(kotlin, 1).
decl(dart, 0).
decl(go, 0).
decl(javascript, 0).

interpret(ruby,1).
interpret(python,1).
interpret(f_sharp,1).
interpret(prolog,1).
interpret(c_sharp,0).
interpret(c_plu_plus,0).
interpret(c,0).
interpret(asm,0).
interpret(swift, 0).
interpret(kotlin, 0).
interpret(dart, 0).
interpret(go, 0).
interpret(javascript, 1).

oop(ruby,3).
oop(c_sharp,3).
oop(python,2).
oop(c_plu_plus,2).
oop(f_sharp,1).
oop(prolog,1).
oop(c,0).
oop(asm,0).
oop(swift, 3).
oop(dart, 2).
oop(kotlin, 2).
oop(go, 1).
oop(javascript, 1).

cross(ruby,1).
cross(python,1).
cross(c_plu_plus,1).
cross(prolog,1).
cross(c,1).
cross(asm,1).
cross(c_sharp,0).
cross(f_sharp,0).
cross(swift, 0).
cross(dart, 1).
cross(kotlin, 1).
cross(go, 1).
cross(javascript, 1).

visual(c_sharp,3).
visual(ruby,2).
visual(python,2).
visual(c_plu_plus,2).
visual(f_sharp,2).
visual(prolog,1).
visual(c,0).
visual(asm,0).
visual(swift, 2).
visual(dart, 2).
visual(kotlin, 2).
visual(go, 1).
visual(javascript, 3).

mobile(c_sharp,0).
mobile(ruby,0).
mobile(python,0).
mobile(c_plu_plus,0).
mobile(f_sharp,0).
mobile(prolog,0).
mobile(c,0).
mobile(asm,0).
mobile(swift, 1).
mobile(dart, 1).
mobile(kotlin, 1).
mobile(go, 0).
mobile(javascript, 0).

microservices(c_sharp, 0).
microservices(ruby, 0).
microservices(c_plu_plus, 0).
microservices(f_sharp, 0).
microservices(prolog, 0).
microservices(c, 0).
microservices(asm, 0).
microservices(swift, 0).
microservices(dart, 0).
microservices(kotlin, 0).
microservices(python, 1).
microservices(go, 2).
microservices(javascript, 2).

question1(X1):-	write("Is your language high level?"),nl,
				write("1. Yes"),nl,
				write("0. NO"),nl,
				read(X1).

question2(X2):-	write("Is your language declarative?"),nl,
				write("1. Yes"),nl,
				write("0. NO"),nl,
				read(X2).

question3(X3):-	write("Is your language interpret?"),nl,
				write("1. Yes"),nl,
				write("0. NO"),nl,
				read(X3).

question4(X4):-	write("Does your language support OOP?"),nl,
				write("3. It is OOP itself"),nl,
				write("2. yes"),nl,
				write("1. yes, but very hard"),nl,
				write("0. NO"),nl,
				read(X4).

question5(X5):-	write("Is your language crossplatformic?"),nl,
				write("1. Yes"),nl,
				write("0. NO"),nl,
				read(X5).

question6(X6):-	write("Does your language support visual interface for user?"),nl,
				write("3. It is visual itself"),nl,
				write("2. yes"),nl,
				write("1. yes, but very hard"),nl,
				write("0. NO"),nl,
				read(X6).

question7(X7):-	write("Is your language for mobile phones?"),nl,
				write("1. Yes"),nl,
				write("0. NO"),nl,
				read(X7).

question_microservices(X8):- write("Is this language popular for developing microservices?"), nl,
				write("2. Yes, it is very popular"), nl,
				write("1. Yes"), nl,
				write("0. No"), nl,
				read(X8).



pr:-	question1(X1),question2(X2),question3(X3),question4(X4),
		question5(X5),question6(X6),question7(X7),question_microservices(X8),
		high(X,X1),decl(X,X2),interpret(X,X3),oop(X,X4),
		cross(X,X5),visual(X,X6),mobile(X,X7),microservices(X, X8),
		write(X).
