# Labo3

Un patron MVC fonctionnel 

Un patron Command fonctionnel

Plusieurs patron Singleton fonctionnel

Un patron Observer fonctionnel 

Donc actuellement, on respecte les spécifications du labo pour les patrons ! 

La logique implémentée selon les patrons : 

Controller = Brain il ne fait pas grand chose lui même mais il sait ce qu'il se passe sur la vue et il sait ce 			 qu'il faut faire en conséquence. 

Actions via Command  = Execute les instructions demandées par le Controller sur le Model et les enregistrent 					  					   dans le record. 

Les commandes sont séparées en plusieurs classes par actions respectives avec la configuration de chaque actions à faire selon l'opération (execute, undo, redo).

Model = Conserve toutes les infos de notre application (c'est cette classe qu'il faudra sérialisé pour 		l'enregistrement de l'image : voir patron Mémento). Il doit avertir les vues de se mettre à jour 		(méthode notifyAllObserver). Il ne le fait pas présentement, c'est le Controller qui lance la méthode, 		il faudrait mettre le lancement de la méthode dans les méthodes set du model ou faire une fonction 		plus globale de la mis à jour des données et lancer la mise à jour des vues à la fin de cette méthode 		(c'est clairement la solution la plus optimale pour éviter trop de redondance.

VueDonnes = Affiche les données du model au format "brut".

VueImage = Affiche les données du model de facon graphique.

La méthode update appelle juste repaint qui permet de repeindre l'interface au complet. Nos éléments graphiques sont tous contenus dans un JPanel dont on a édité la méthode paintComponent qui sera appelée au moment du repaint. Ainsi, l'interface se remet à jour automatiquement avec les données actuelles peu importe ce qui arrive sur l'interface.

Il reste à faire :

Sauvegarde de l'image : il y a tout à faire ou presque (voir patron mémento).

Drag : il faut revoir la logique du drag

Nettoyer les incohérences (telles que le notifyAllObservers appelé par le Controller)

La VueDonnée est commencé mais il reste à faire. Je suis ouvert à afficher toutes données pertinentes dans cette page.

Le KeyBoardListener : il n'a pas été du tout fait.

Similarité dans les commandes : différentes écoutes pour une seule commande, donc un patron adapter serait bon)