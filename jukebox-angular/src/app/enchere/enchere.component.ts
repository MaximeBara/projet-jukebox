import { Component, OnInit } from '@angular/core';
import { EnchereService } from '../services/enchere.service';
import { Enchere } from '../models/enchere';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-enchere',
  templateUrl: './enchere.component.html',
  styleUrls: ['./enchere.component.css']
})
export class EnchereComponent implements OnInit {

  encheres: Enchere[] = [];
  userId = 0;

  constructor(private enchereService: EnchereService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.userId = this.tokenStorageService.getUser().id;
    this.enchereService.getAllEncheresByUser(this.userId).subscribe((encheres: Enchere[]) => {
      this.encheres = encheres;

      console.log(this.encheres)
    });

    
  }

}
