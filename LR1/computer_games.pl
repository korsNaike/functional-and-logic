multiplayer(among_us, 1).
multiplayer(apex_legends, 1).
multiplayer(call_of_duty, 3).
multiplayer(counter_strike, 1).
multiplayer(cyberpunk_2077, 2).
multiplayer(dota2, 1).
multiplayer(fifa, 3).
multiplayer(forest, 3).
multiplayer(fortnite, 1).
multiplayer(gta_v, 3).
multiplayer(league_of_legends, 1).
multiplayer(lego_marvel, 2).
multiplayer(minecraft, 3).
multiplayer(mortal_combat, 3).
multiplayer(overwatch, 1).
multiplayer(pubg, 1).
multiplayer(rocket_league, 1).
multiplayer(skyrim, 2).
multiplayer(valorant, 1).
multiplayer(world_of_warcraft, 1).

genre(among_us, 6).
genre(apex_legends, 7).
genre(call_of_duty, 4).
genre(counter_strike, 4).
genre(cyberpunk_2077, 5).
genre(dota2, 3).
genre(fifa, 9).
genre(forest, 1).
genre(fortnite, 7).
genre(gta_v, 8).
genre(league_of_legends, 3).
genre(lego_marvel, 8).
genre(minecraft, 1).
genre(mortal_combat, 8).
genre(overwatch, 4).
genre(pubg, 7).
genre(rocket_league, 2).
genre(skyrim, 5).
genre(valorant, 4).
genre(world_of_warcraft, 5).

cybersport(among_us, 4).
cybersport(apex_legends, 3).
cybersport(call_of_duty, 4).
cybersport(counter_strike, 1).
cybersport(cyberpunk_2077, 2).
cybersport(dota2, 1).
cybersport(fifa, 3).
cybersport(forest, 2).
cybersport(fortnite, 1).
cybersport(gta_v, 2).
cybersport(league_of_legends, 1).
cybersport(lego_marvel, 2).
cybersport(minecraft, 4).
cybersport(mortal_combat, 3).
cybersport(overwatch, 4).
cybersport(pubg, 1).
cybersport(rocket_league, 3).
cybersport(skyrim, 2).
cybersport(valorant, 1).
cybersport(world_of_warcraft, 2).

franchise(among_us, 2).
franchise(apex_legends, 2).
franchise(call_of_duty, 1).
franchise(counter_strike, 1).
franchise(cyberpunk_2077, 2).
franchise(dota2, 2).
franchise(fifa, 1).
franchise(forest, 1).
franchise(fortnite, 2).
franchise(gta_v, 1).
franchise(league_of_legends, 2).
franchise(lego_marvel, 1).
franchise(minecraft, 0).
franchise(mortal_combat, 1).
franchise(overwatch, 2).
franchise(pubg, 2).
franchise(rocket_league, 2).
franchise(skyrim, 1).
franchise(valorant, 2).
franchise(world_of_warcraft, 1).

graphic(among_us, 1).
graphic(apex_legends, 4).
graphic(call_of_duty, 2).
graphic(counter_strike, 2).
graphic(cyberpunk_2077, 2).
graphic(dota2, 4).
graphic(fifa, 2).
graphic(forest, 2).
graphic(fortnite, 1).
graphic(gta_v, 2).
graphic(league_of_legends, 1).
graphic(lego_marvel, 1).
graphic(minecraft, 3).
graphic(mortal_combat, 2).
graphic(overwatch, 1).
graphic(pubg, 2).
graphic(rocket_league, 4).
graphic(skyrim, 2).
graphic(valorant, 1).
graphic(world_of_warcraft, 1).

% question_multiplayer(-X)
question_multiplayer(X_multiplayer):-	write("Is the game primarily focused on multiplayer gameplay?"),nl,
				            write("1. Yes"),nl,
				            write("2. NO"),nl,
				            write("3. Both multiplayer and single player games are possible"),nl,
				            read(X_multiplayer).

% question_genre(-X)
question_genre(X_genre):- write("What genre prevails in this game?"),nl,
                    write("1. Survival Sandbox"),nl,
                    write("2. Arcade game"),nl,
                    write("3. MOBA"),nl,
                    write("4. The shooter"),nl,
                    write("5. RPG"),nl,
                    write("6. Individual"),nl,
                    write("7. Battle Royale"),nl,
                    write("8. Action-Adventure"),nl,
                    write("9. Sports simulator"),nl,
                    read(X_genre).

% question_esports(-X)
question_esports(X_esport):-  write("Is the game popular on the esports scene?"),nl,
                    write("1. Yes"),nl,
                    write("2. No"),nl,
                    write("3. Rather yes"),nl,
                    write("4. Probably not"),nl,
                    read(X_esport).

% question_franchise(-X)
question_franchise(X_franchise):-  write("Is the game part of the franchise?"),nl,
                    write("1. Yes"),nl,
                    write("2. No"),nl,
                    read(X_franchise).

% question_graphics(-X)
question_graphics(X_graphic):-  write("What kind of graphics is in this game?"),nl,
                    write("1. Cartoon"),nl,
                    write("2. Realistic"),nl,
                    write("3. Low-poly or pixel"),nl,
                    write("4. Mixed (cartoon-realistic)"),nl,
                    read(X_graphic).

% multiplayer_genre(?Y, +X_multiplayer, +X_genre)
multiplayer_genre(Y, X_genre, X_genre):- multiplayer(Y, X_multiplayer), genre(Y, X_genre).

% multiplayer_genre_esports(?Y, +X_multiplayer, +X_genre, +X_esport)
multiplayer_genre_esports(Y, X_genre, X_genre, X_esport):- multiplayer(Y, X_multiplayer), 
                                                            genre(Y, X_genre), 
                                                            cybersport(Y, X_esport).

% multiplayer_genre_esports_franchise(?Y, +X_multiplayer, +X_genre, +X_esport, +X_franchise)
multiplayer_genre_esports_franchise(Y, X_multiplayer, X_genre, X_esport, X_franchise):- multiplayer(Y, X_multiplayer), 
                                                                                        genre(Y, X_genre), 
                                                                                        cybersport(Y, X_esport), 
                                                                                        franchise(Y, X_franchise).

% multiplayer_genre_esports_franchise(?Y, +X_multiplayer, +X_genre, +X_esport, +X_franchise, +X_graphic)
multiplayer_genre_esports_franchise_graphic(Y, X_multiplayer, X_genre, X_esport, X_franchise, X_graphic):- multiplayer(Y, X_multiplayer), 
                                                                                                            genre(Y, X_genre), 
                                                                                                            cybersport(Y, X_esport), 
                                                                                                            franchise(Y, X_franchise), 
                                                                                                            graphic(Y, X_graphic).

% play/0
play:-	question_multiplayer(X_multiplayer),
        findall(Y, multiplayer(Y, X_multiplayer), Res_multiplayer),
        length(Res_multiplayer, Len_multiplayer), 
        (Len_multiplayer =:= 1 -> writeln(Res_multiplayer), false ; true),

        question_genre(X_genre),
        findall(Y, multiplayer_genre(Y, X_multiplayer, X_genre), Res_genre),
        length(Res_genre, Len_genre), 
        (Len_genre =:= 1 -> writeln(Res_genre), false ; true),

        question_esports(X_esport),
        findall(Y, multiplayer_genre_esports(Y, X_multiplayer, X_genre, X_esport), Res_esport),
        length(Res_esport, Len_esport),
        (Len_esport =:= 1 -> write(Res_esport), false ; true),

        question_franchise(X_franchise),
        findall(Y, multiplayer_genre_esports_franchise(Y, X_multiplayer, X_genre, X_esport, X_franchise), Res_franchise),
        length(Res_franchise, Len_franchise),
        (Len_franchise =:= 1 -> write(Res_franchise), false ; true),

		question_graphics(X_graphics),
        multiplayer_genre_esports_franchise_graphic(X, X_multiplayer, X_genre, X_esport, X_franchise, X_graphics),
		write(X).