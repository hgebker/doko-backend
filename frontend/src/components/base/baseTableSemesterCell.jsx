import DataTableCell from '@salesforce/design-system-react/components/data-table/cell';
import { SEMESTER_LABEL } from 'constants/semester.js';

export default function BaseTableSemesterCell({ children, ...props }) {
  return <DataTableCell {...props}>{SEMESTER_LABEL[children]}</DataTableCell>;
}
BaseTableSemesterCell.displayName = DataTableCell.displayName;
