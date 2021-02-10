package main;

public class ParamsFromFront {

    private String method;
    private String card;

    // ParamsFromFront (String method){
    //     System.out.println(method);
    //     this.method = method;
    // }    
    ParamsFromFront (String card, String method){
        System.out.println(card);
        System.out.println(method);
        this.method = method;
        this.card = card;
    } 
    
    // public void setMethod (String method){
    //     this.method = method;
    // }

    public String getMethod() {
        return this.method;
    }
    public String getCard() {
        return this.card;
    }
}

// class ResponseToFront {

//     private String method;

//     ResponseToFront (String method){
//         this.method = method;
//     }    
    
//     public void setMethod (String method){
//         this.method = method;
//     }

//     public String getMethod() {
//         return(this.method);
//     }
// }
