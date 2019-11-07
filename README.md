# Ex-01-BusinessLogic-EJB

Si sviluppi un applicazione Web J2EE per la gestione del magazzino di un ipotetico negozio di componenti informatiche.

Tale applicazione deve avere l’aspetto di un sito e-commerce e che quindi dia la possibilità ad un utente di mettere le componenti elettroniche nel carrello e successivamente di inviare l’ordine d’acquisto.

Gli ordini poi (mediante Hibernate e/o JPA) dovranno essere salvati permanentemente in un DB.

Per realizzare l’applicazione si consideri il seguente modello:
1. Prodotti (componenti per pc: processori, ram, schede video ecc..)
2. Produttori (amd, intel, nvidia ecc..)
3. Carrello (SessionBean in attesa di essere ordinati)
4. Ordini (EntiyBean che devono essere salvati nel data base


si richiede di:
-  realizzare la parte di logica di accesso al database (pattern DAO) mediante componenti Enterprise Java Beans 3.0 In particolare:
  +  Utilizzare mapping Object-Relational tramite componenti Entity Bean
  +  Realizzare DAO tramite opportuni componenti Session Bean

-  inoltre, estendere la logica applicativa già descritta, aggiungendo un meccanismo di logging (potenzialmente) remoto:
  +  Ciascun metodo che preveda scritture su DB (aggiunta di nuovi componenti, produttori, ordini ecc…) deve inviare un messaggio JMS a un opportuno componente di logging
  + Componente di logging (realizzato come Message Driven Bean) scrive su opportuno log (anche solo stdout) il messaggio ricevuto
