# Android Natural Language UI Testing
# Guide de l’utilisateur

## 1. Introduction
Ce document a pour but de servir de mode d’emploi pour les utilisateurs du système. Il comprend les contraintes concernant la rédaction des cas de test, une explication des étapes de la configuration du système pour en faire son utilisation et une explication des règles à suivre pour modifier le comportement du traitement du langage naturel.

## 2. Utilisation du logiciel
L’utilisation de ce logiciel comporte plusieurs étapes :
1. L’utilisateur crée un dossier où résideront les descriptions de test à traduire en code Java. Le chemin vers ce dossier sera demandé par la suite lors de la configuration du script d’exécution en Python.
2. L’utilisateur rédige des cas de test en se référant à la section 3 de ce document.
3. L’utilisateur lance le script run.py se trouvant à la racine du projet.
4. S’il s’agit de la première utilisation, le script doit être configuré. Pour ce faire, l’utilisateur se référe à la section 4.
5. Toujours avec le script, l’utilisateur lance le projet.
6. L’utilisateur:
   1. lance le projet Test runner avec l’environnement de développement Android Studio;
   2. effectue la synchronisation Gradle;
   3. lance le projet;
   4. visualise les résultats.
Pour plus d’informations sur la configuration d’Android Studio, l’utilisateur peut se référer à la section 6.

## 3. Rédaction des cas de test
D’abord, les tests doivent être rédigésen anglais.

Ensuite, une instruction doit être contenue dans une seule phrase. Une instruction peut, par exemple, être de demander au programme d’appuyer sur un bouton ou de demander si la valeur dans une boîte de texte correspond à la valeur voulue.

De plus, une instruction de type assert doit commencer par « Verify that ». Une instruction de type assert est ce qu’utilise JUnit pour déterminer si une propriété dans le programme correspond à ce qu’elle devrait être. Par exemple, ce type d’instruction en langage naturel pourrait être « Verify that the input box is not empty ».

Finalement, une description de test doit toujours commencer de l’écran d’accueil du téléphone (home screen).

Voici un exemple simple de ce que peut avoir l’air un cas de test en langage naturel : Click on ‘Apps’. Click on ‘Settings’. Click on ‘Wi-Fi’. Click on the switch.

## 4. Configuration du script
L'utilisateur doit modifier les emplacements spécifiés dans le script d'exécution Python. Ce script peut être trouvé à la racine du projet.

Ces variables correspondent a des chemins nécessaires vers des dossiers ou des fichiers que le système doit utiliser dans le but de fonctionner correctement. Cette section tente d’offrir une explication de chacune des variables pour que les utilisateurs puissent configurer le projet plus facilement.

### 4.1 Variable 'ANLUIT_DESCRIPTIONS'
Correspond à l'emplacement du dossier où se trouvent les descriptions de test en langage naturel. Un fichier texte doit correspondre à une seule description de test. Un exemple de ce dossier se trouve dans ce dépôt à l'emplacement data/descriptions.

### 4.2 Variable 'ANLUIT_RULES'
Correspond à l'emplacement où se trouvent les règles de décodage du sens des phrases. Ce fichier doit être sous le format JSON et respecter la structure établie. Un exemple de ce fichier se trouve dans ce dépôt à l'emplacement data/rules.json.

### 4.3 Variable 'ANLUIT_SENTENCE_MODEL'
Corresponds au fichier entrainé utilisé par la librairie OpenNLP. Ce fichier est utilisé pour extraire les phrases dans les textes. Un exemple de ce fichier se trouve dans ce dépôt à l'emplacement data/en-sent.bin. Il peut être également trouvé sur le site officiel de OpenNLP, soit à l'adresse http://opennlp.sourceforge.net/models-1.5/.

### 4.4 Variable 'ANLUIT_RESULTS'
Corresponds à l'emplacement où le fichier contenant les résultats de l'extraction du sens du langage naturel se trouvera. Ce fichier sera généré par le système, il n'est donc pas nécessaire qu'il existe préalablement, un exemple de ce fichier se trouve tout de même dans le dépôt à l'emplacement data/results.json.

### 4.5 Variable 'ANLUIT_GENERATED_CLASS'
Corresponds à l'emplacement où le fichier de code source Java généré se trouvera. Cet emplacement devrait être dans le dossier src du projet tr pour accélérer le processus d'utilisation de ce projet.

## 5. Configuration du fichier de règles
Cette section s’adresse seulement aux utilisateurs plus expérimentés qui veulent s’aventurer dans la personnalisation des règles utilisées par le logiciel pour réaliser le traitement du langage naturel.

### 5.1 Modification des règles
Les règles utilisées pour faire le traitement du langage naturel peuvent être modifiées facilement à l’aide d’un éditeur de texte. Le format JSON doit toujours être respecté pour préserver le bon fonctionnement du système, un exemple de fichier de règle est disponible à l’annexe A. Chaque phrase de chaque description de test sera soumise à toutes les règles se trouvant dans le fichier de règle.

Voici une simple règle :
```json
{
        "regex":".*(and|with) (the)? text '(.*)'.*",
        "groups":
        [
                {
                        "index":"3",
                        "type":"text",
                        "reliability":"10"
                }
        ]
}
```

L’attribut regex correspond à une expression régulière. Si la phrase qui est en cours de traitement ne correspond pas à l’expression régulière décrite, le système poursuit son traitement et passe à la prochaine règle. Dans le cas contraire, le traitement de cette règle sur la phrase débute. Pour chaque groupe, le système va regarder s’il y a déjà une règle avec le type et une reliability plus faible. Si la réponse est positive, le système a trouvé un sens à la phrase et l’emmagasine en mémoire.

### 5.2 Modification des synonymes
Voici la description des synonymes dans le fichier de règles fourni à l’annexe A :

```json
"synonyms":
[
        {"click":["press", "tap", "go"]},
        {"write":["enter"]}
]
```

Les synonymes permettent d’éviter l’écriture de plusieurs règles très semblable. Par exemple, sans la définition {"write":["enter"]}, l’utilisateur aurait a écrire une règle avec l’expression régulière write ‘(.*)’ on (.*) et une autre avec l’expression régulière enter ‘(.*)’ on (.*).

## 6. Configuration Android Studio
Si la synchronisation Gradle ne se fait pas à l’ouverture d’Android Studio, l’utilisateur peut lancer la synchronisation manuellement avec le bouton ci-dessous, celui-ci se trouve dans la barre d’outils.

Par la suite, l’utilisateur doit créer une configuration pour le projet. L’utilisateur doit appuyer sur le bouton surligné visible ci-dessous.

La configuration pour lancer le projet doit être semblable à celle ci-dessous.

## Annexes
### A. Exemple de fichier de règles
```json
{
        "synonyms":
        [
                {"click":["press", "tap", "go"]},
                {"write":["enter"]}
        ],
        "rules":
        [
                {
                        "regex":".*(and|with) (the)? text '(.*)'.*",
                        "groups":
                        [
                                {
                                        "index":"3",
                                        "type":"text",
                                        "reliability":"10"
                                }
                        ]
                },
                {
                        "regex":".*(and|with) (the)? description '(.*)'.*",
                        "groups":
                        [
                                {
                                        "index":"3",
                                        "type":"description",
                                        "reliability":"10"
                                }
                        ]
                },
                {
                        "regex":".*(and|with) (the)? id '(.*)'.*",
                        "groups":
                        [
                                {
                                        "index":"3",
                                        "type":"id",
                                        "reliability":"10"
                                }
                        ]
                },
                {
                        "regex":"(click) (on)? (the)?.*",
                        "groups":
                        [
                                {
                                        "index":"1",
                                        "type":"action",
                                        "reliability":"10"
                                }
                        ]
                },
                {
                        "regex":".*on '(.*)'.*",
                        "groups":
                        [
                                {
                                        "index":"1",
                                        "type":"text",
                                        "reliability":"10"
                                }
                        ]
                },
                {
                        "regex":".*on the (switch|button|icon).*",
                        "groups":
                        [
                                {
                                        "index":"1",
                                        "type":"component",
                                        "reliability":"10"
                                }
                        ]
                },
        ]
}
```
