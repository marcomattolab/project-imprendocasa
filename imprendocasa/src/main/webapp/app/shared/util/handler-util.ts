import {Subscription} from 'rxjs';

export function closeSubscription(subscription: Subscription) {
    if (subscription) {
        subscription.unsubscribe();
    }
}
