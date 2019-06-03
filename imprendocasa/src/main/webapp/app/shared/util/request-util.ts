import {HttpParams} from '@angular/common/http';

export const createRequestOption = (req?: any): HttpParams => {
    let options: HttpParams = new HttpParams();
    if (req) {
        Object.keys(req).forEach(key => {
            if (key !== 'sort') {
                options = options.set(key, req[key]);
            }
        });
        if (req.sort) {
            req.sort.forEach(val => {
                options = options.append('sort', val);
            });
        }
    }
    return options;
};

export const checkAndCompileSearchFilterEquals = (formControls, searchFilter, field: string) => {
    const value = formControls[field].value;
    console.log(value);
    if (value !== '') {
        searchFilter[`${field}.equals`] = value;
    }
    return searchFilter;
};

export const checkAndCompileSearchFilterContains = (formControls, searchFilter, field: string) => {
    const value = formControls[field].value;

    if (value !== '') {
        searchFilter[`${field}.contains`] = value;
    }
    return searchFilter;
};

/** MMA - ATTENZIONE VANNO ABOLITI E CHIAMATO checkAndCompileSearchBetween invece che questo controlliamo e testiamo!!! */
export const checkAndCompileSearchFilterGt = (formControls, searchFilter, field: string) => {
    const value = formControls[field].value;

    // taglio l'ultima lettere per allinerlo alla richiesta su db
    field = field.slice(0, -1);
    if (value !== '') {
        searchFilter[`${field}.greaterOrEqualThan`] = value;
    }
    return searchFilter;
};

/** MMA - ATTENZIONE VANNO ABOLITI E CHIAMATO checkAndCompileSearchBetween invece che questo controlliamo e testiamo!!!*/
export const checkAndCompileSearchFilterLt = (formControls, searchFilter, field: string) => {
    const value = formControls[field].value;

    // taglio l'ultima lettere per allinerlo alla richiesta su db
    field = field.slice(0, -1);
    if (value !== '') {
        searchFilter[`${field}.lessOrEqualThan`] = value;
    }
    return searchFilter;
};

export const checkAndCompileSearchBetween = (formControls, searchFilter, fieldDa: string, fieldA: string, fieldName: string) => {
    const valueDa = formControls[fieldDa].value;
    const valueA = formControls[fieldA].value;
    if (valueDa !== '') {
        searchFilter[`${fieldName}.greaterOrEqualThan`] = valueDa;
    }
    if (valueA !== '') {
        searchFilter[`${fieldName}.lessOrEqualThan`] = valueA;
    }
    return searchFilter;
};
