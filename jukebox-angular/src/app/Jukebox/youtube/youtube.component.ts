import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Titre } from 'src/app/models/titre';

@Component({
  selector: 'app-youtube',
  templateUrl: './youtube.component.html',
  styleUrls: ['./youtube.component.css'],
})
export class YoutubeComponent implements OnInit {
  @ViewChild('player') player: any;
  videoId!: string;
  @Input() titre!: Titre;
  @Input()
  set id(id: string) {
    this.videoId = id;
  }
  @Output() next = new EventEmitter<string>();

  ngOnInit() {
    const tag = document.createElement('script');
    tag.src = 'https://www.youtube.com/iframe_api';
    document.body.appendChild(tag);
    this.videoId = this.titre.lien;
  }


  onReady() {
    this.player.playVideo();
  }

  onStateChange(event: any) {
    switch (event.data) {
      case 0:
        this.changeVideo();
        break;
      case 1:
        break;
      case 2:
        if(this.player.getCurrentTime() == 0) this.player.playVideo();
    }
  }

  changeVideo() {
    this.next.emit('complete');
  }
}
