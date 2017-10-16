import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Stock {
  id: number;
  name: string;
  currentPrice: number;
  lastUpdate: string;
}

@Component({
  selector: 'app-root',
  template: `<ul *ngFor="let post of posts ">
              <li >{{post.id}}</li>
              <li >{{post.name}}</li>
              <li >{{post.currentPrice}}</li>
              <li >{{post.lastUpdate}}</li>
            `
})

export class AppComponent {
  public posts = [];

  constructor(private http: HttpClient){}

  ngOnInit(): void {
    this.http.get<Stock[]>('http://stock-api.test:8080/api/stocks/').subscribe(data => {this.posts = data});
    console.log(this.posts);
  }


}


