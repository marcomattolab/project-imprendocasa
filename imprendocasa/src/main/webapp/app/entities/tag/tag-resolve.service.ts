import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Tag} from 'app/shared/model/tag.model';
import {TagService} from 'app/entities/tag/tag.service';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {HttpResponse} from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class TagResolve implements Resolve<Tag> {
    constructor(private service: TagService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Tag> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Tag>) => response.ok),
                map((tag: HttpResponse<Tag>) => tag.body)
            );
        }
        return of(new Tag());
    }
}
