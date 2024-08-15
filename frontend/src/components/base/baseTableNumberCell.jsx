import FormattedNumberField from 'components/base/formattedNumberField';
import DataTableCell from '@salesforce/design-system-react/components/data-table/cell';

export default function BaseTableNumberCell({ children, ...props }) {
  return (
    <DataTableCell {...props}>
      <FormattedNumberField value={children} />
    </DataTableCell>
  );
}
BaseTableNumberCell.displayName = DataTableCell.displayName;
