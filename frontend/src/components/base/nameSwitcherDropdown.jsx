import { LIST_OPTIONS } from 'constants/semester';

import Dropdown from '@salesforce/design-system-react/components/menu-dropdown';

const SELECTION_CATEGORIES = [
  {
    id: 'gesamt',
    label: 'Gesamt'
  },
  { type: 'divider' },
  ...LIST_OPTIONS
];

export default function NameSwitcherDropdown({ onSelect }) {
  return (
    <Dropdown
      buttonClassName="slds-button_icon-small"
      buttonVariant="icon"
      iconCategory="utility"
      iconName="down"
      align="right"
      options={SELECTION_CATEGORIES}
      onSelect={onSelect}
    />
  );
}
