import VerticalNavigation from '@salesforce/design-system-react/components/vertical-navigation';
import { SEMESTER_OPTIONS } from 'constants/semester';

const SELECTION_CATEGORIES = [
  {
    id: 'Allgemein',
    label: 'Allgemein',
    items: [
      {
        id: 'gesamt',
        label: 'Gesamt'
      }
    ]
  },
  ...SEMESTER_OPTIONS
];

export default function SemesterSelection({ onSelect, selectedSemester }) {
  const handleSemesterSelect = (_, data) => {
    onSelect(data.item);
  };

  return (
    <VerticalNavigation
      categories={SELECTION_CATEGORIES}
      selectedId={selectedSemester.id}
      onSelect={handleSemesterSelect}
      className="slds-theme_default slds-box"
    />
  );
}
