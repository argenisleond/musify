import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Musify App';

  options = {
    showProgressBar: true,
    pauseOnHover: true,
    timeOut: 5000
  };
}
