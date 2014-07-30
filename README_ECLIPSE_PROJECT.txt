##############################################
# README                                     #
# Auteurs : CGTEAM                           #
##############################################

1.0 Edition du code source et importation du projet sur Eclipse.

  1.1 Installation de appcompat_v7 comme librarie d'Eclipse

  - Aller sur le SDK Manager
  - Télécharger le paquet Android Support Library (dans Extras -> Android Support Library)
  - Aller sur Eclipse
  - Créer un projet Android avec comme version minimum l'API 10 (version 2.3.3)
  - Supprimer le projet Android après que celui ci est été généré (sans supprimer appcompat_v7)

  2.2 Importation du projet

  - Cloner le projet (cf Github)
  - Aller Eclipse, importer un nouveau projet (File->New->New Project->Android->Android Project from Existing Code)

  2.3 Liaison de la librarie appcompat_v7

  - Aller sur Eclipse, sur votre projet, clique droit puis aller dans Properties->Android. En bas dans "library" supprimer toutes les libraries associés,
  puis ajouter appcompat_v7.
  - Enfin aller sur votre dossier du projet puis modifier le fichier project.properties
  modifier le 'X' de la ligne target=android-X par '20' 
