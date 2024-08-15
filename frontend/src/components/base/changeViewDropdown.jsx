import Dropdown from '@salesforce/design-system-react/components/menu-dropdown';

import { VIEWS } from '../evenings/eveningOverview';

export default function ChangeViewDropdown({ selectedView, onSelect }) {
  return (
    <Dropdown
      buttonVariant="icon"
      checkmark
      iconCategory="utility"
      iconName={selectedView.rightIcon.name}
      iconSize="large"
      iconVariant="more"
      align="right"
      onSelect={onSelect}
      options={VIEWS}
      value={selectedView.value}
      menuPosition="overflowBoundaryElement"
    />
  );
}
