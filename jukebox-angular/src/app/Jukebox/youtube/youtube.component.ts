import { Component, Input, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-youtube',
  templateUrl: './youtube.component.html',
  styleUrls: ['./youtube.component.css']
})
export class YoutubeComponent implements OnInit {
  @ViewChild('player') player: any;
  videoId: string = "";

  @Input()
  set id(id: string) {
    this.videoId = id;
  }

  ngOnInit() {
    const tag = document.createElement('script');
    tag.src = 'https://www.youtube.com/iframe_api';
    document.body.appendChild(tag);
  }

  // Autoplay
  onReady() {
    this.player.mute();         
    this.player.playVideo();    
  }

  // Loop
  onStateChange(event :any) {
    if (event.data === 0) {
      this.player.playVideo();  
    }
  }

}
