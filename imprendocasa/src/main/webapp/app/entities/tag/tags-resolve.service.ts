import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {Tag} from 'app/shared/model/tag.model';
import {TagService} from 'app/entities/tag/tag.service';
import {HttpResponse} from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class TagsResolve implements Resolve<Tag[]> {
    constructor(private tagService: TagService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Tag[]> {
        return this.tagService.query();
    }
}
