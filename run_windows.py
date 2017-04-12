#!/usr/bin/env python
# -*- coding: utf-8 -*-
#title           :run.py
#description     :This program provides a command line interface for the ANLUIT project.
#author          :simon
#date            :01/02/2016
#version         :0.1
#usage           :py run.py
#notes           :
#python_version  :3.+  
#=======================================================================
 
import sys, os, webbrowser
 
def main_menu():
    print("\nChoisir une option :")
    print("1. Lire la documentation utilisateur")
    print("2. Configurer les variables ANLUIT")
    print("3. Lancer ANLUIT")
    print("\n0. Quitter")
    choice = input(" >> ")
    exec_menu(choice)
 
def exec_menu(choice):
    ch = choice.lower()
    if ch == '':
        menu_actions['main_menu']()
    else:
        try:
            menu_actions[ch]()
        except KeyError:
            print("Sélection invalide.\n")
            menu_actions['main_menu']()
    main_menu()

def read_user_guide():
    if (os.path.isfile('README.md')):
        os.system('README.md')
    else:
        webbrowser.open('https://bitbucket.org/o9simon/anluit/overview/', new=2)

def setup_anluit_vars():
	inp = ""
	print("ANLUIT_DESCRIPTIONS (" + os.getenv('ANLUIT_DESCRIPTIONS', '') + ")")
	inp = input(" >> ")
	if (inp != ""):
		os.environ["ANLUIT_DESCRIPTIONS"] = inp
	print("ANLUIT_RULES (" + os.getenv('ANLUIT_RULES', '') + ")")
	inp = input(" >> ")
	if (inp != ""):
		os.environ["ANLUIT_RULES"] = inp
	print("ANLUIT_SENTENCE_MODEL (" + os.getenv('ANLUIT_SENTENCE_MODEL', '') + ")")
	inp = input(" >> ")
	if (inp != ""):
		os.environ["ANLUIT_SENTENCE_MODEL"] = inp
	print("ANLUIT_RESULTS (" + os.getenv('ANLUIT_RESULTS', '') + ")")
	inp = input(" >> ")
	if (inp != ""):
		os.environ["ANLUIT_RESULTS"] = inp
	print("ANLUIT_GENERATED_CLASS (" + os.getenv('ANLUIT_GENERATED_CLASS', '') + ")")
	inp = input(" >> ")
	if (inp != ""):
		os.environ["ANLUIT_GENERATED_CLASS"] = inp

def launch_anluit():
    print(">>> NLP")
    os.system('java -cp nlp\\bin;nlp\\libs\\json-simple-1.1.1.jar;nlp\\libs\\opennlp-tools-1.6.0.jar anluit.nlp.Main %ANLUIT_DESCRIPTIONS% %ANLUIT_RULES% %ANLUIT_SENTENCE_MODEL% %ANLUIT_RESULTS%')
    print(">>> CG")
    os.system('java -cp cg\\bin;cg\\libs\\json-simple-1.1.1.jar anluit.cg.Main %ANLUIT_RESULTS% %ANLUIT_GENERATED_CLASS%')

def exit():
    sys.exit()

def default_anluit_vars():
	os.environ["ANLUIT_DESCRIPTIONS"] = "C:\\Users\\simon\\Documents\\workspace\\anluit\\data\\descriptions\\"
	os.environ["ANLUIT_RULES"] = "C:\\Users\\simon\\Documents\\workspace\\anluit\\data\\rules.json"
	os.environ["ANLUIT_SENTENCE_MODEL"] = "C:\\Users\\simon\\Documents\\workspace\\anluit\\data\\en-sent.bin"
	os.environ["ANLUIT_RESULTS"] = "C:\\Users\\simon\\Documents\\workspace\\anluit\\data\\results.json"
	os.environ["ANLUIT_GENERATED_CLASS"] = "C:\\Users\\simon\\Documents\\workspace\\anluit\\tr\\app\\src\\main\\java\\anluit\\tr\\GeneratedTest.java"

menu_actions = {
    'main_menu': main_menu,
    '1': read_user_guide,
    '2': setup_anluit_vars,
    '3': launch_anluit,
    '0': exit,
}
 
if __name__ == "__main__":
	default_anluit_vars()
	main_menu()