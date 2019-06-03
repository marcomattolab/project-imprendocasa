import {catchError} from 'rxjs/operators';

const PROPERTY_LONG_NAME = 'long_name';
const PROPERTY_SHORT_NAME = 'short_name';
const CASE7 = 7;
const CASE8 = 8;
const ZOOM = 12;

export class MapsData {
    latitude: number;
    longitude: number;
}

export const autoComplete = (currentObject, searchElementRef, ngZone, zoom, mapsData) => {

    const autocomplete = new window['google'].maps.places.Autocomplete(searchElementRef.nativeElement, {
        types: ['address']
    });

    autocomplete.addListener('place_changed', () => {
        ngZone.run(() => {
            // get the place result
            const place: google.maps.places.PlaceResult = autocomplete.getPlace();
            let n = 0;
            const placeLenght = place.address_components.length;

            try {
                switch (placeLenght) {
                    case CASE7: {
                        currentObject.indirizzo = place.address_components[n][PROPERTY_LONG_NAME];
                        currentObject.cap = place.address_components[n + 6][PROPERTY_LONG_NAME];
                        break;
                    }
                    case CASE8: {
                        n += 1;
                        currentObject.indirizzo = place.address_components[n][PROPERTY_LONG_NAME] + ',' +
                            place.address_components[n - 1][PROPERTY_LONG_NAME];
                        currentObject.cap = place.address_components[n + 6][PROPERTY_LONG_NAME];
                        break;
                    }
                    default: {
                        console.log('errore, inserire numero civico');
                        break;
                    }
                }

                currentObject.citta = place.address_components[n + 1][PROPERTY_LONG_NAME];
                currentObject.provincia = place.address_components[n + 3][PROPERTY_SHORT_NAME];
                currentObject.regione = place.address_components[n + 4][PROPERTY_LONG_NAME];

                // verify result
                if (place.geometry === undefined || place.geometry === null) {
                    return;
                }
                console.log(place.geometry.location.lat());
                mapsData.latitude = place.geometry.location.lat();
                mapsData.longitude = place.geometry.location.lng();
                zoom = ZOOM;

            } catch (e) {
                this.jhiAlertService.error(e, null, null);
            }

        });
    });
};
