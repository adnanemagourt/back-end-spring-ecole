package com.example.back.DTO;


import com.example.back.entities.Classe;
import com.example.back.entities.Matiere;
import com.example.back.entities.Personne;
import lombok.NonNull;

public class CustomResponseRequest {

    public static class Response{
        @NonNull public Integer code;
        public Response(@NonNull Integer code){
            this.code = code;
        }
    }

//    public static class ResponseLogin extends Response{
//        public Personne user;
//        public String type;
//    }

    public static class ResponseError extends Response{
        @NonNull public String err;
        public ResponseError(@NonNull Integer code,@NonNull String err){
            super(code);
            this.err = err;
        }
    }

    public static class ResponseList extends Response{
        public Iterable data;
        public ResponseList(@NonNull Integer code, @NonNull Iterable data){
            super(code);
            this.data = data;
        }
    }

    public static class ResponseListPersonne extends Response{
        public Iterable<Personne> data;
        public ResponseListPersonne(@NonNull Integer code, @NonNull Iterable<Personne> data){
            super(code);
            this.data = data;
        }
    }

    public static class ResponsePersonne extends Response{
        public Personne data;
        public ResponsePersonne(@NonNull Integer code, @NonNull Personne data){
            super(code);
            this.data = data;
        }
    }

    public static class ResponseListMatiere extends Response{
        public Iterable<Matiere> data;
        public ResponseListMatiere(@NonNull Integer code, @NonNull Iterable<Matiere> data){
            super(code);
            this.data = data;
        }
    }

    public static class ResponseMatiere extends Response{
        public Matiere data;
        public ResponseMatiere(@NonNull Integer code, @NonNull Matiere data){
            super(code);
            this.data = data;
        }
    }

    public static class ResponseListClasse extends Response{
        public Iterable<Classe> data;
        public ResponseListClasse(@NonNull Integer code, @NonNull Iterable<Classe> data){
            super(code);
            this.data = data;
        }
    }

    public static class ResponseClasse extends Response{
        public Classe data;
        public ResponseClasse(@NonNull Integer code, @NonNull Classe data){
            super(code);
            this.data = data;
        }
    }

    public static class LoginRequestBody{
        public String email;
        public String password;
        public String type;
    }

    // for the rest of the requests, the request body is the same as the class representing it

}
