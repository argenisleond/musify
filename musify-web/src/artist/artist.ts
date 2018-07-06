import {Style} from '../style/style';
import {People} from '../people/people';

export class Artist{
  id: number;
  name: string;
  year: string;
  styles: Style[];
  members: People[];
  related: Artist[];
}
