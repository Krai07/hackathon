import { Directive, ElementRef, EventEmitter, HostListener, Input, Output, Renderer2 } from '@angular/core';
import { AutocompleteService } from './autocomplete.service';
import { NgModel } from '@angular/forms';

@Directive({
    selector: '[appAutocomplete]'
})
export class AutocompleteDirective {
    @Input('appAutocomplete') model: any; // ngModel reference
    @Output() modelChange: EventEmitter<any> = new EventEmitter<any>();
    private popup: HTMLElement;
    private table: HTMLTableElement;
    private styleElement: HTMLStyleElement;

    constructor(
        private el: ElementRef,
        private renderer: Renderer2,
        private autocompleteService: AutocompleteService,
        private ngModel: NgModel
    ) { }

    @HostListener('input', ['$event'])
    onInput(event: any): void {
        const value = event.target.value;

        if (value.trim() === '') {
            this.destroyPopup();
        } else {
            const matches = this.autocompleteService.filterAssetData(value);
            this.createPopup(matches);
            this.createStyleElement();
        }
    }

    private createStyleElement(): void {
        this.styleElement = this.renderer.createElement('style');
        this.renderer.appendChild(document.head, this.styleElement);

        const styles = `
            .autocomplete-popup {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                z-index: 999;
              }
              
              .autocomplete-card {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                padding: 20px;
                max-height: 80vh; /* Adjust the maximum height of the card */
                overflow-y: auto;
              }
              
              
              .autocomplete-table {
                width: 100%;
                border-collapse: collapse;
              }
              
              .autocomplete-table th, .autocomplete-table td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
              }
              
              .autocomplete-table th {
                background-color: #f2f2f2;
              }
              
              .autocomplete-table button {
                padding: 6px 12px;
                background-color: #51CBCE;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
              }
              
              .autocomplete-table button:hover {
                background-color: #51CBCF;
                border: none;
              }
          `;

        this.renderer.appendChild(this.styleElement, this.renderer.createText(styles));
    }

    private createPopup(matches: any[]): void {
        this.destroyPopup();
      
        this.popup = this.renderer.createElement('div');
        this.renderer.addClass(this.popup, 'autocomplete-popup');
      
        // Create a card element
        const card = this.renderer.createElement('div');
        this.renderer.addClass(card, 'autocomplete-card');
      
        if (matches.length === 0) {
          // If no matches, display a message
          const noAssetMsg = this.renderer.createElement('p');
          this.renderer.appendChild(noAssetMsg, this.renderer.createText('No Asset Found'));
          this.renderer.appendChild(card, noAssetMsg);
        } else {
          // Create the table element
          this.table = this.renderer.createElement('table');
          this.renderer.addClass(this.table, 'autocomplete-table');
      
          // Add border to the table
          this.renderer.setStyle(this.table, 'border', '1px solid #ccc'); // Adjust border style and color here
      
          // Create table header
          const headerRow = this.renderer.createElement('tr');
      
          const headerLabels = ['Asset Code', 'Processor', 'RAM', 'HDD/SSD', 'OS', 'Actions'];
      
          headerLabels.forEach(label => {
            const headerCell = this.renderer.createElement('th');
            this.renderer.appendChild(headerCell, this.renderer.createText(label));
            this.renderer.appendChild(headerRow, headerCell);
          });
      
          this.renderer.appendChild(this.table, headerRow);
      
          // Create table rows
          matches.forEach(asset => {
            const row = this.renderer.createElement('tr');
            Object.keys(asset).forEach(property => {
              const cell = this.renderer.createElement('td');
              const cellValue = asset[property] !== '' ? asset[property] : '-';
              this.renderer.appendChild(cell, this.renderer.createText(cellValue));
              this.renderer.appendChild(row, cell);
            });
      
            // Add a button to each row
            const buttonCell = this.renderer.createElement('td');
            const button = this.renderer.createElement('button');
            this.renderer.appendChild(button, this.renderer.createText('Add Asset'));
            this.renderer.listen(button, 'click', () => this.onButtonClick(asset.asset_code));
            this.renderer.appendChild(buttonCell, button);
            this.renderer.appendChild(row, buttonCell);
      
            this.renderer.listen(row, 'click', () => this.onSuggestionClick(asset.asset_code));
            this.renderer.appendChild(this.table, row);
          });
      
          // Append the table to the card
          this.renderer.appendChild(card, this.table);
        }
      
        // Append the card to the popup
        this.renderer.appendChild(this.popup, card);
      
        // Center the popup on the screen
        const windowWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        const windowHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
      
        const popupWidth = this.popup.offsetWidth;
        const popupHeight = this.popup.offsetHeight;
      
        const popupLeft = (windowWidth - popupWidth) / 2;
        const popupTop = (windowHeight - popupHeight) / 2;
      
        this.renderer.setStyle(this.popup, 'position', 'fixed');
        //   this.renderer.setStyle(this.popup, 'top', `${popupTop}px`);
        //   this.renderer.setStyle(this.popup, 'left', `${popupLeft}px`);
        this.renderer.setStyle(this.popup, 'top', `0px`);
        this.renderer.setStyle(this.popup, 'left', `200px`);
      
        this.renderer.appendChild(document.body, this.popup);
      }
      

    // New method for button click
    public onButtonClick(assetCode: string): void {
        console.log(`Button clicked for asset code: ${assetCode}`);
        // Add your button click logic here
    }

    public destroyPopup(): void {
        if (this.popup) {
            this.renderer.removeChild(document.body, this.popup);
            this.popup = null;
            this.table = null;
        }
    }

    public onSuggestionClick(selectedValue: string): void {
        this.model = selectedValue;
        console.log(this.model);
        this.renderer.setProperty(this.el.nativeElement, 'value', this.model); // Update the input value
        this.ngModel.viewToModelUpdate(this.model);
        this.destroyPopup();
    }
}
