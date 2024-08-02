package co.edu.uptc.view.controller;

import co.edu.uptc.model.User;
import co.edu.uptc.persistence.ManagementSupplier;
import co.edu.uptc.view.listeners.*;
import co.edu.uptc.view.panels.MainPanel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public class ViewController {

   private ManagementSupplier managementSupplier = new ManagementSupplier();
   private MainPanel mainPanel;
   private ListenersLoginMenu listenersLoginMenu;
   private ListenersHeaderMenu listenerHeaderMenu;
   private ListenersUpdateMenu listenersUpdateMenu;
   private ListenersModifyMenu listenerModifyMenu;
   private ListenersDeleteMenu listenersDeleteMenu;
   private ListenersAddMenu listenersAddMenu;
   private  ListenersGraphicalMenu listenersGraphicalMenu;
   List<User> listUser = new ArrayList<>();

   public void setListUser(List<User> listUser) {
      this.listUser = listUser;
   }

   public ViewController (){
      this.mainPanel = new MainPanel();
      this.listenersLoginMenu = new ListenersLoginMenu(this);
      this.listenerHeaderMenu = new ListenersHeaderMenu(this);
      this.listenersUpdateMenu = new ListenersUpdateMenu(this);
      this.listenerModifyMenu = new ListenersModifyMenu(this);
      this.listenersDeleteMenu = new ListenersDeleteMenu(this);
      this.listenersAddMenu = new ListenersAddMenu(this);
      this.listenersGraphicalMenu = new ListenersGraphicalMenu(this);
      dumpFileJSON();
      loadFileJSON();
   }

   public void loadFileJSON() {
      String fileName = "resources/data/login/user.json";
      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
         Gson gson = new Gson();
         Type userListType = new TypeToken<List<User>>() {}.getType();
         List<User> users= gson.fromJson(br, userListType);
         if (users != null) {
            this.listUser.addAll(users);
            System.err.println("Información cargada correctamente");
         } else {
            System.err.println("No se encontraron proveedores en el archivo JSON.");
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void dumpFileJSON() {
      String rutaArchivo = "resources/data/login/user.json";
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String jsonString = gson.toJson(this.listUser);
      this.writeFile(rutaArchivo, jsonString);
   }

   protected void writeFile(String rutaNombreArchivo, String content) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaNombreArchivo))) {
         writer.write(content);
      } catch (IOException e) {
         System.out.println("Error");
      }
   }

   public ManagementSupplier getManagementSupplier() {
      return managementSupplier;
   }

   public void setManagementSupplier(ManagementSupplier managementSupplier) {
      this.managementSupplier = managementSupplier;
   }


   public void getListJSON(){
      managementSupplier.loadFileJSON();
   }

   public void getListCSV(){
      managementSupplier.loadFilePlain(managementSupplier.getConfValue().getPath().concat(managementSupplier.getConfValue().getNameFileCSV()));
   }

   public void getListSER(){
      managementSupplier.loadFileSerializate();
   }

   public void getLisTXT(){
      managementSupplier.loadFilePlain(managementSupplier.getConfValue().getPath().concat(managementSupplier.getConfValue().getNameFileTXT()));
   }

   public void getListXML(){
      managementSupplier.loadFileXML(managementSupplier.getConfValue().getPath().concat(managementSupplier.getConfValue().getNameFileXML()));
   }


   public ListenersLoginMenu getListenersLoginMenu() {
      return listenersLoginMenu;
   }

   public void setListenersLoginMenu(ListenersLoginMenu listenersLoginMenu) {
      this.listenersLoginMenu = listenersLoginMenu;
   }

   public ListenersHeaderMenu getListenerHeaderMenu() {
      return listenerHeaderMenu;
   }

   public void setListenerHeaderMenu(ListenersHeaderMenu listenerHeaderMenu) {
      this.listenerHeaderMenu = listenerHeaderMenu;
   }

   public ListenersUpdateMenu getListenersUpdateMenu() {
      return listenersUpdateMenu;
   }

   public void setListenersUpdateMenu(ListenersUpdateMenu listenersUpdateMenu) {
      this.listenersUpdateMenu = listenersUpdateMenu;
   }

   public MainPanel getMainPanel() {
      return mainPanel;
   }

   public void setMainPanel(MainPanel mainPanel) {
      this.mainPanel = mainPanel;
   }

   public ListenersModifyMenu getListenerModifyMenu() {
      return listenerModifyMenu;
   }

   public void setListenerModifyMenu(ListenersModifyMenu listenerModifyMenu) {
      this.listenerModifyMenu = listenerModifyMenu;
   }

   public List<User> getListUser() {
      return listUser;
   }

   public ListenersDeleteMenu getListenersDeleteMenu() {
      return listenersDeleteMenu;
   }

   public void setListenersDeleteMenu(ListenersDeleteMenu listenersDeleteMenu) {
      this.listenersDeleteMenu = listenersDeleteMenu;
   }

   public ListenersAddMenu getListenersAddMenu() {
      return listenersAddMenu;
   }

   public void setListenersAddMenu(ListenersAddMenu listenersAddMenu) {
      this.listenersAddMenu = listenersAddMenu;
   }

   public ListenersGraphicalMenu getListenersGraphicalMenu() {
      return listenersGraphicalMenu;
   }

   public void setListenersGraphicalMenu(ListenersGraphicalMenu listenersGraphicalMenu) {
      this.listenersGraphicalMenu = listenersGraphicalMenu;
   }
}