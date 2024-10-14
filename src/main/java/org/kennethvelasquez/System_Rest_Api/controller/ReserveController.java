package org.kennethvelasquez.System_Rest_Api.controller;

import org.kennethvelasquez.System_Rest_Api.model.Reserve;
import org.kennethvelasquez.System_Rest_Api.model.User;
import org.kennethvelasquez.System_Rest_Api.service.ReserveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/reserve/")
public class ReserveController implements ReserveService {
    private static ArrayList<Reserve> listReserves;
    private static HashMap<User, Reserve> listUserReserves = new HashMap<>();
    private UserController userController = new UserController();

    public ReserveController() {
        listReserves = new ArrayList<>();
    }

    @PostMapping("/")
    @Override
    public ResponseEntity addReserve(@RequestBody Reserve reserve) {
        listReserves.add(reserve);
        return ResponseEntity.ok().body(reserve);
    }

    @GetMapping("/")
    @Override
    public ResponseEntity getAllReserves() {
        return ResponseEntity.ok(listReserves.stream().toList());
    }

    @GetMapping("/{idReserve}")
    @Override
    public ResponseEntity getReserveById(@PathVariable int idReserve) {
        Optional <Reserve> reserve = listReserves.stream().filter(
                reserveFind -> reserveFind.getIdReserve() == idReserve).findFirst();
        if (reserve.isPresent()) {
            return ResponseEntity.ok(reserve);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idReserve}")
    @Override
    public ResponseEntity updateReserve(@RequestBody Reserve reserve, @PathVariable int idReserve) {
        Optional <Reserve> reserveSearch = listReserves.stream().filter(
                reserveFind -> reserveFind.getIdReserve() == idReserve).findFirst();
        if(reserveSearch.isPresent()) {
            reserveSearch.get().setReserveDate(reserve.getReserveDate());
            reserveSearch.get().setDescription(reserve.getDescription());
            return ResponseEntity.ok(reserveSearch);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idReserve}")
    @Override
    public ResponseEntity deleteReserveById(@PathVariable int idReserve) {
        Optional <Reserve> reserve = listReserves.stream().filter(
                reserveFind -> reserveFind.getIdReserve() == idReserve).findFirst();
        if(reserve.isPresent()) {
            listReserves.remove(reserve.get());
            return ResponseEntity.ok("Se ha eliminado la reserva: "+reserve.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/userReserve")
    public ResponseEntity assignUserReserve (@RequestParam int idReserve, @RequestParam Long idUser) {
        ArrayList <User> userList = userController.getLisUsers();
        Optional <User> userSearch = userList.stream().filter(userFind->userFind.getId() == idUser).findFirst();
        Optional <Reserve> reserveSearch = listReserves.stream().filter(
                reservFind -> reservFind.getIdReserve() == idReserve).findFirst();
        if(!userSearch.isPresent() || !reserveSearch.isPresent()) {
            return ResponseEntity.status(404).body("No se pudo asignar, verifique los ID");
        }else{
            listUserReserves.put(userSearch.get(), reserveSearch.get());
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/allUserReserve")
    public ResponseEntity allUserReservation(){
        ArrayList <String> listAll = new ArrayList<>();
        List<User> listKey = listUserReserves.keySet().stream().toList();
        for(User user : listKey){
            listAll.add("El usuario "+user + " Tiene reserva "+listUserReserves.get(user));
        }
        return ResponseEntity.ok(listAll.stream().toList());
    }

    @DeleteMapping("/deleteUserReserve")
    public ResponseEntity deleteUserReserve(@RequestParam int idReserve, @RequestParam Long idUser) {
        ArrayList <User> listUser = userController.getLisUsers();
        Optional <User> userSearch = listUser.stream().filter(x-> x.getId()==idUser).findFirst();
        Optional <Reserve> reserveSearch = listReserves.stream().filter(x-> x.getIdReserve()==idReserve).findFirst();
        if(userSearch.isPresent() && reserveSearch.isPresent()) {
            listUserReserves.remove(userSearch.get(), reserveSearch.get());
            return ResponseEntity.ok("Se ha eliminado la asignacion de reserva");
        }else{
            return ResponseEntity.status(404).body("No se ha eliminado la reserva");
        }
    }
}
