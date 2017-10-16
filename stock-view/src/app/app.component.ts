import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';




interface Stock {
  id: number;
  name: string;
  currentPrice: number;
  lastUpdate: string;
}

interface Post {
    userId: number;
    id:number;
    title:string;
}

@Component({
  selector: 'app-root',
  template: `Oláss <li *ngFor="let post of posts ">{{post.id}}</li>`
})

export class AppComponent {
  public posts = [];

  constructor(private http: HttpClient){}

  ngOnInit(): void {
    this.http.get<Stock[]>('http://localhost:8080/api/stocks/').subscribe(data => {this.posts = data});
    console.log(this.posts);
  }


}


