## Configuration de la base de données

Avant d'exécuter l'application, assurez-vous de définir les variables d'environnement suivantes avec les valeurs appropriées :

- `USER_DATABASE` : Le nom d'utilisateur pour votre base de données.
- `PASSWORD_DATABASE` : Le mot de passe pour votre base de données.

### Paramétrage des variables d'environnement

#### Windows

- Ligne de commande (doit être lancée en tant qu'administrateur) :

  ```cmd
  setx USER_DATABASE "votre_nom_d'utilisateur"
  setx PASSWORD_DATABASE "votre_mot_de_passe"
  ```

- Interface graphique :
    - Ouvrez le Panneau de configuration.
    - Recherchez et sélectionnez "Système".
    - Cliquez sur "Paramètres système avancés".
    - Dans l'onglet "Avancé", cliquez sur "Variables d'environnement".
    - Cliquez sur "Nouveau" sous "Variables utilisateur" ou "Variables système" (selon que vous voulez que la variable soit disponible pour un seul utilisateur ou tous les utilisateurs)
    - Entrez le nom de la variable et sa valeur.

#### macOS et Linux
Pour définir des variables d'environnement sous macOS ou Linux, vous pouvez les ajouter à votre fichier de profil shell (.bashrc, .bash_profile, .zshrc, etc.) :

 ```bash
  export USER_DATABASE="votre_nom_d'utilisateur"
  export PASSWORD_DATABASE="votre_mot_de_passe"
  ```

Après avoir ajouté ces lignes, vous devez redémarrer votre shell ou exécuter source ~/.bashrc (ou le nom de votre fichier de profil) pour que les nouvelles variables prennent effet.

Notez que ces variables d'environnement seront disponibles pour toutes les sessions shell et tous les processus lancés à partir de ces sessions. Si vous voulez qu'elles soient disponibles uniquement pour un processus spécifique, vous pouvez les définir juste avant de lancer ce processus, comme ceci :

```bash
USER_DATABASE="votre_nom_d'utilisateur" PASSWORD_DATABASE="votre_mot_de_passe" java -jar votre_application.jar
```


Remplacez  "votre_nom_d'utilisateur" et "votre_mot_de_passe" par les informations appropriées pour votre configuration.

Assurez-vous également de remplacer "votre_application.jar" par le nom correct de votre fichier